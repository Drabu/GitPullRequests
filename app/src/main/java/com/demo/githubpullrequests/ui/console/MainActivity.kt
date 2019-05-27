package com.demo.githubpullrequests.ui.console

import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.githubpullrequests.R
import com.demo.githubpullrequests.data.model.view.GithubViewModel
import com.demo.githubpullrequests.databinding.ActivityMainBinding
import com.demo.githubpullrequests.ui.adapters.PullRequestAdapter
import com.demo.githubpullrequests.ui.base.BaseActivity
import com.demo.githubpullrequests.utils.AppConstants.LoadingManager.*
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding, GithubViewModel>() {


    lateinit var pullRequestRVAdapter: PullRequestAdapter

    var layoutManager = LinearLayoutManager(this)

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getMyViewModel(): GithubViewModel =
        ViewModelProviders.of(this, factory).get(GithubViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setObservers()

        viewModel.requestPullRequestsFor("octocat", "Hello-World")
    }


    private fun setObservers() {
        viewModel.getPullRequestDataStream().observe(this, Observer {
            pullRequestRVAdapter.setItems(it)
        })

        viewModel.getLoadingManager().observe(this, Observer {

            when (it) {

                STATE_REFRESHING -> {
                    Timber.d("STATE REFRESHING")
                    pullRequestRVAdapter.notifyDataSetChanged()
                }

                STATE_COMPLETED -> {
                    pullRequestRVAdapter.notifyDataSetChanged()
                    Timber.d("STATE COMPLETED")
                }

                STATE_NO_INTERNET -> {
                    Timber.d("STATE NO INTERNET")
                }

                STATE_NO_MORE_PAGES -> {
                    Toast.makeText(this, "No More pages left", Toast.LENGTH_LONG).show()
                    pullRequestRVAdapter.notifyDataSetChanged()
                }

                else -> {
                    Timber.d("NO STATE")
                }
            }
        })

    }

    override fun setUiComponents() {
        setOpenRequestsRV()
    }

    private fun setOpenRequestsRV() {
        pullRequestRVAdapter = PullRequestAdapter()
        getViewBinding().pullRequestsRV.addItemDecoration(DividerItemDecoration(this, VERTICAL))
        getViewBinding().pullRequestsRV.layoutManager = layoutManager
        getViewBinding().pullRequestsRV.adapter = pullRequestRVAdapter

        getViewBinding().pullRequestsRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                updateCardsCount()
                if (isEligibleToFetchData()) {
                    viewModel.pagingManager.isScrolling = false
                    fetchData()
                }

            }

            private fun isEligibleToFetchData(): Boolean {
                return viewModel.pagingManager.isScrolling
                        && (viewModel.pagingManager.currentItems + viewModel.pagingManager.scrolledOutItems ==
                        viewModel.pagingManager.totalItems
                        && viewModel.getLoadingManager().value != STATE_REFRESHING
                        && viewModel.getLoadingManager().value != STATE_NO_MORE_PAGES)

            }

            private fun updateCardsCount() {
                viewModel.pagingManager.currentItems = layoutManager.childCount
                viewModel.pagingManager.scrolledOutItems = layoutManager.findFirstVisibleItemPosition()
                viewModel.pagingManager.totalItems = layoutManager.itemCount
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                    viewModel.pagingManager.isScrolling = true
            }
        })
    }

    private fun fetchData() {
        Timber.d("Fetch page number %s ", ++viewModel.pagingManager.pageNumber)
        viewModel.requestPullRequestsFor("octocat", "Hello-World", page = viewModel.pagingManager.pageNumber)
    }


}
