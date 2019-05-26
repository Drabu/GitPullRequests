package com.demo.githubpullrequests.data.api_client

import com.demo.githubpullrequests.data.model.api.PullRequests
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("")
    fun getListOfPullRequests(): Observable<List<PullRequests>>
}