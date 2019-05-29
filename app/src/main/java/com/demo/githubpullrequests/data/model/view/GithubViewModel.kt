package com.demo.githubpullrequests.data.model.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.demo.githubpullrequests.data.model.api.PullRequests
import com.demo.githubpullrequests.data.repositories.GithubRepository
import com.demo.githubpullrequests.utils.AppConstants
import com.demo.githubpullrequests.utils.AppConstants.FIRST_PAGE
import com.demo.githubpullrequests.utils.AppConstants.PagingManager

/**
 *@author Burhan ud din ---> View Model to hold data of list to survive configuration changes
 */
class GithubViewModel(application: Application, private val githubRepository: GithubRepository) :
    AndroidViewModel(application) {

    val pagingManager = PagingManager()

    /**
     *@author Burhan ud din ---> request for list of repositories
     * @param githubUser reefers to github username
     * @param repositoryName reefers to the repository
     * @param page reefers to page number
     */
    fun requestPullRequestsFor(githubUser: String, repositoryName: String, page: Int = FIRST_PAGE) {
        githubRepository.requestPullRequestsFor(githubUser = githubUser, repositoryName = repositoryName, page = page)
    }

    /**
     *@author Burhan ud din ---> get data stream for list of pull requests
     */
    fun getPullRequestDataStream(): LiveData<ArrayList<PullRequests?>> = githubRepository.getPullRequestDataStream()

    /**
     *@author Burhan ud din ---> get loading status of pull requests
     */
    fun getLoadingManager(): LiveData<AppConstants.LoadingManager> = githubRepository.getLoadingManager()

}