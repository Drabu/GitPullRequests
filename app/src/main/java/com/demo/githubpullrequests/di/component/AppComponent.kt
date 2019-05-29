package com.demo.githubpullrequests.di.component

import android.app.Application
import com.demo.githubpullrequests.GithubApplication
import com.demo.githubpullrequests.di.builder.ActivityBuilder
import com.demo.githubpullrequests.di.modules.AppModule
import com.demo.githubpullrequests.di.modules.RESTModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

/**
 *@author Burhan ud din ---> Component part of dagger
 */
@Component(modules = [AndroidSupportInjectionModule::class, RESTModule::class, ActivityBuilder::class, AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        /**
         *@author Burhan ud din ---> Provides application context
         */
        @BindsInstance
        fun application(application: Application): Builder

        /**
         *@author Burhan ud din ---> Used for builder
         */
        fun bind(): AppComponent

    }

    /**
     *@author Burhan ud din ---> inject to application module
     */
    fun inject(githubApplication: GithubApplication)

}