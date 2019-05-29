package com.demo.githubpullrequests

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.demo.githubpullrequests.di.component.DaggerAppComponent
import com.demo.githubpullrequests.utils.AppConstants
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import kotlin.math.log

/**
*@author Burhan ud din ---> Main Application class
*/
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

        RxJavaPlugins.setErrorHandler {
            sendBroadcast(Intent(AppConstants.NO_DATA))
        }

    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjectorActivity

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjectorFragment


}