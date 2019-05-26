package com.demo.githubpullrequests.di.modules

import android.app.Application
import com.demo.githubpullrequests.data.api_client.ApiService
import com.demo.githubpullrequests.data.repositories.GithubRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun providesGithubRepository(application: Application, apiService: ApiService): GithubRepository =
        GithubRepository(apiService, application)

}