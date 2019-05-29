package com.demo.githubpullrequests.ui.console

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Parcelable
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
import com.demo.githubpullrequests.utils.AppConstants
import com.demo.githubpullrequests.utils.AppConstants.LoadingManager.*
import kotlinx.android.parcel.Parcelize
import timber.log.Timber
import timber.log.Timber.d

/**
 *@author Burhan ud din ---> Activity hosts the list of pull requests
 */
class MainActivity : BaseActivity<ActivityMainBinding, GithubViewModel>() {

    private lateinit var pullRequestRVAdapter: PullRequestAdapter

    private lateinit var config: Config

    val layoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getDataFromIntent()

        registerMyReceiver()

    }

    private fun getDataFromIntent() {

        if (intent.extras != null && intent.hasExtra(CONFIG)) {

            config = intent.getParcelableExtra(CONFIG)
            setObservers()
            viewModel.requestPullRequestsFor(config.userName, config.userRepo)
        } else {
            Toast.makeText(this, "Please enter a valid username and repository combination!", Toast.LENGTH_LONG).show()
            finish()
        }

    }

    private fun setObservers() {
        viewModel.getPullRequestDataStream().observe(this, Observer {
            pullRequestRVAdapter.setItems(it)
        })

        viewModel.getLoadingManager().observe(this, Observer {

            when (it) {

                STATE_REFRESHING -> {
                    d("STATE REFRESHING")
                    pullRequestRVAdapter.notifyDataSetChanged()
                }

                STATE_COMPLETED -> {
                    pullRequestRVAdapter.notifyDataSetChanged()
                    d("STATE COMPLETED")
                }

                STATE_NO_INTERNET -> {
                    d("STATE NO INTERNET")
                    Toast.makeText(this, "Please connect to network and ", Toast.LENGTH_LONG).show()
                    finish()
                }

                STATE_NO_MORE_PAGES -> {
                    Toast.makeText(this, "No More pages left", Toast.LENGTH_LONG).show()
                    pullRequestRVAdapter.notifyDataSetChanged()
                }

                STATE_NO_PULL_REQUESTS -> {
                    Toast.makeText(this, "No Pull requests", Toast.LENGTH_LONG).show()
                    finish()
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
        d("Fetch page number %s ", ++viewModel.pagingManager.pageNumber)
        viewModel.requestPullRequestsFor(config.userName, config.userRepo, page = viewModel.pagingManager.pageNumber)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getMyViewModel(): GithubViewModel =
        ViewModelProviders.of(this, factory).get(GithubViewModel::class.java)

    private fun registerMyReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(AppConstants.NO_DATA)
        registerReceiver(noDataReceiver, intentFilter)
    }

    private val noDataReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Toast.makeText(p0, "No Repositories where found with the provided combination!", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    @Parcelize
    class Config(val userName: String, val userRepo: String) : Parcelable

    companion object {
        const val CONFIG = "CONFIG"
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(noDataReceiver)
    }
}
