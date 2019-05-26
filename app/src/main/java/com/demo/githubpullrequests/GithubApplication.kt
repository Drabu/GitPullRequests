package com.demo.githubpullrequests

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.demo.githubpullrequests.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class GithubApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjectorActivity: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingAndroidInjectorFragment: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .bind()
            .inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjectorActivity

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjectorFragment


}