package com.demo.githubpullrequests.di.modules

import android.app.Application
import com.demo.githubpullrequests.data.api_client.ApiService
import com.demo.githubpullrequests.data.repositories.GithubRepository
import dagger.Module
import dagger.Provides

/**
 *@author Burhan ud din ---> Dagger component
 */
@Module
class AppModule {

    /**
     *@author Burhan ud din ---> provides github repository
     */
    @Provides
    fun providesGithubRepository(application: Application, apiService: ApiService): GithubRepository =
        GithubRepository(apiService, application)

}