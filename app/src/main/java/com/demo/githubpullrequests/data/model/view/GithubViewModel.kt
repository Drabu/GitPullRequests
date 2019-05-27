package com.demo.githubpullrequests.data.model.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.githubpullrequests.data.model.api.PullRequests
import com.demo.githubpullrequests.data.repositories.GithubRepository
import com.demo.githubpullrequests.utils.AppConstants
import com.demo.githubpullrequests.utils.AppConstants.FIRST_PAGE
import com.demo.githubpullrequests.utils.AppConstants.PagingManager

class GithubViewModel(application: Application, private val githubRepository: GithubRepository) :
    AndroidViewModel(application) {

    val pagingManager = PagingManager()

    fun requestPullRequestsFor(githubUser: String, repositoryName: String, page: Int = FIRST_PAGE) {
        githubRepository.requestPullRequestsFor(githubUser = githubUser, repositoryName = repositoryName, page = page)
    }

    fun getPullRequestDataStream(): LiveData<ArrayList<PullRequests?>> = githubRepository.getPullRequestDataStream()

    fun getLoadingManager() : LiveData<AppConstants.LoadingManager> = githubRepository.getLoadingManager()

}