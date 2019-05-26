package com.demo.githubpullrequests.data.model.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.demo.githubpullrequests.data.model.api.PullRequests
import com.demo.githubpullrequests.data.repositories.GithubRepository

class GithubViewModel(application: Application, var githubRepository: GithubRepository) :
    AndroidViewModel(application) {


    fun requestPullRequestsFor(githubUser: String, repositoryName: String) =
        githubRepository.requestPullRequestsFor(githubUser = githubUser, repositoryName = repositoryName)

    fun getPullRequestDataStream(): LiveData<List<PullRequests>> = githubRepository.getPullRequestDataStream()

}