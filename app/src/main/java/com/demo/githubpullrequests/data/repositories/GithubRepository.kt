package com.demo.githubpullrequests.data.repositories

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.githubpullrequests.data.api_client.ApiService
import com.demo.githubpullrequests.data.model.api.PullRequests
import com.demo.githubpullrequests.utils.AppConstants.LoadingManager
import com.demo.githubpullrequests.utils.AppConstants.LoadingManager.*
import com.demo.githubpullrequests.utils.AppConstants.PAGE_SIZE
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
class GithubRepository(var apiService: ApiService, var application: Application) {
    private val pullRequestDataStream: MutableLiveData<ArrayList<PullRequests?>> = MutableLiveData()

    init {
        pullRequestDataStream.value = ArrayList()
    }

    private var loadingManager = MutableLiveData<LoadingManager>()

    fun getLoadingManager(): LiveData<LoadingManager> = loadingManager

    fun getPullRequestDataStream(): LiveData<ArrayList<PullRequests?>> = pullRequestDataStream

    fun requestPullRequestsFor(githubUser: String, repositoryName: String, page: Int) {

        //add progress to view
        pullRequestDataStream.value?.add(null)
        loadingManager.postValue(STATE_REFRESHING)
        apiService.getListOfPullRequests(
            githubUser,
            repositoryName,
            perPage = PAGE_SIZE,
            page = page.toString()
        )
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .delay(5, TimeUnit.SECONDS)
            .subscribe(object : Observer<ArrayList<PullRequests?>> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(it: ArrayList<PullRequests?>) {

                    //remove circular progress bar
                    if (pullRequestDataStream.value!!.size >= 0)
                        pullRequestDataStream.value!!.removeAt(pullRequestDataStream.value!!.size - 1)

                    //stop appending and update
                    if (it.size == 0) {
                        loadingManager.postValue(STATE_NO_MORE_PAGES)
                        return
                    }


                    //append incoming to main list
                    pullRequestDataStream.value!!.addAll(it)

                    //update the live data stream
                    pullRequestDataStream.postValue(pullRequestDataStream.value)

                    //update the progress
                    loadingManager.postValue(STATE_COMPLETED)

                }

                override fun onError(e: Throwable) {
                    Toast.makeText(application, "Error " + e.message, Toast.LENGTH_LONG).show()
                }

            })

    }


}