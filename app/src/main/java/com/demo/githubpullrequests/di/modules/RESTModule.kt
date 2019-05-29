package com.demo.githubpullrequests.di.modules

import com.demo.githubpullrequests.data.api_client.ApiService
import com.demo.githubpullrequests.utils.AppConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
*@author Burhan ud din ---> Provides rest module to dagger.
*/
@Module
class RESTModule {

    /** @author Burhan ud din ---> okhttp for logging and interception*/
    @Provides
    fun getOkhttpClient(): OkHttpClient {
        val okhttpClient = OkHttpClient.Builder()
        okhttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        okhttpClient.connectTimeout(10, TimeUnit.SECONDS)
        okhttpClient.retryOnConnectionFailure(true)
        return okhttpClient.build()
    }

    /** provides retrofit instance*/
    @Provides
    fun getInstance(okhttpClient: OkHttpClient): ApiService {
        val retrofit =
            Retrofit.Builder().baseUrl(AppConstants.BASE_URL).client(okhttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(ApiService::class.java)
    }

}