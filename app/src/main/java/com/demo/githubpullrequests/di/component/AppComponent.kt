package com.demo.githubpullrequests.di.component

import android.app.Application
import com.demo.githubpullrequests.GithubApplication
import com.demo.githubpullrequests.di.builder.ActivityBuilder
import com.demo.githubpullrequests.di.modules.AppModule
import com.demo.githubpullrequests.di.modules.RESTModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidSupportInjectionModule::class, RESTModule::class, ActivityBuilder::class, AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun bind(): AppComponent

    }

    fun inject(githubApplication: GithubApplication)

}