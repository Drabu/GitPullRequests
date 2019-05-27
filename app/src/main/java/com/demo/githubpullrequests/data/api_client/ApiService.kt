package com.demo.githubpullrequests.data.api_client

import com.demo.githubpullrequests.data.model.api.PullRequests
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("repos/{username}/{repository}/pulls")
    fun getListOfPullRequests(
        @Path("username") githubUser: String,
        @Path("repository") repositoryName: String,
        @Query("per_page") perPage: String,
        @Query("page") page: String
    ): Observable<ArrayList<PullRequests?>>
}