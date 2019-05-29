package com.demo.githubpullrequests.di.builder

import com.demo.githubpullrequests.ui.console.MainActivity
import com.demo.githubpullrequests.ui.console.UserRepoSelection
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *@author Burhan ud din ---> Module used to inject dagger dependencies
 */
@Module
interface ActivityBuilder {
    /**
     *@author Burhan ud din ---> injects dependencies to main activity
     */
    @ContributesAndroidInjector
    fun getConsoleActivity(): MainActivity

    /**
     *@author Burhan ud din ---> injects dependencies to user repo selection
     */
    @ContributesAndroidInjector
    fun getUserRepoActivity(): UserRepoSelection
}