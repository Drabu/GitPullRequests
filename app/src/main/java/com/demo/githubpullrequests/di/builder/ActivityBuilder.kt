package com.demo.githubpullrequests.di.builder

import com.demo.githubpullrequests.ui.console.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {

    @ContributesAndroidInjector
    fun getConsoleActivity(): MainActivity

}