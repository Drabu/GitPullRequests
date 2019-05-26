package com.demo.githubpullrequests.ui.console

import android.os.Bundle
import android.widget.LinearLayout.VERTICAL
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.githubpullrequests.R
import com.demo.githubpullrequests.data.model.view.GithubViewModel
import com.demo.githubpullrequests.databinding.ActivityMainBinding
import com.demo.githubpullrequests.ui.adapters.PullRequestAdapter
import com.demo.githubpullrequests.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, GithubViewModel>() {


    lateinit var pullRequestRVAdapter: PullRequestAdapter

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getMyViewModel(): GithubViewModel = ViewModelProviders.of(this, factory).get(GithubViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setObservers()
    }


    private fun setObservers() {
        viewModel.getPullRequestDataStream().observe(this, Observer {
            pullRequestRVAdapter.setItems(it)
        })
    }

    override fun setUiComponents() {
        setOpenRequestsRV()
    }

    private fun setOpenRequestsRV() {
        pullRequestRVAdapter = PullRequestAdapter()
        getViewBinding().pullRequestsRV.addItemDecoration(DividerItemDecoration(this, VERTICAL))
        getViewBinding().pullRequestsRV.layoutManager = LinearLayoutManager(this)
        getViewBinding().pullRequestsRV.adapter = pullRequestRVAdapter
    }
}
