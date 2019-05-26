package com.demo.githubpullrequests.data.api_client

import com.demo.githubpullrequests.data.model.api.PullRequests
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("repos/{username}/{repository}/pulls?per_page=20&page=1")
    fun getListOfPullRequests(
        @Path("username") githubUser: String,
        @Path("repository") repositoryName: String
    ): Observable<List<PullRequests>>
}