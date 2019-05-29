package com.demo.githubpullrequests.data.api_client

import com.demo.githubpullrequests.data.model.api.PullRequests
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *@author Burhan ud din ---> Api Service available on remote server
 */
interface ApiService {
    /**
     *@author Burhan ud din ---> get list of pull requests on repository
     * @param githubUser Refers to to user profile id
     * @param repositoryName refers to the name of repository
     * @param perPage refers to the number of items per page
     * @param page refers to the page number
     * @return Observable of List of Pull Requests
     *
     */
    @GET("repos/{username}/{repository}/pulls")
    fun getListOfPullRequests(
        @Path("username") githubUser: String,
        @Path("repository") repositoryName: String,
        @Query("per_page") perPage: String,
        @Query("page") page: String
    ): Observable<ArrayList<PullRequests?>>
}