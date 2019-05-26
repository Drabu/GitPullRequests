package com.demo.githubpullrequests.data.repositories

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.githubpullrequests.data.api_client.ApiService
import com.demo.githubpullrequests.data.model.api.PullRequests
import io.reactivex.android.schedulers.AndroidSchedulers

@SuppressLint("CheckResult")
class GithubRepository(var apiService: ApiService, application: Application) {

    var pullRequestDataStream: MutableLiveData<List<PullRequests>> = MutableLiveData()

    fun getPullRequestDataStream(): LiveData<List<PullRequests>> = pullRequestDataStream


    fun requestPullRequestsFor(githubUser: String, repositoryName: String) {
        apiService.getListOfPullRequests(githubUser, repositoryName)
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                pullRequestDataStream.postValue(it)
            }
    }

}