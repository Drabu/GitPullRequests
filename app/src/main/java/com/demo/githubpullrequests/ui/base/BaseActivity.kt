package com.demo.githubpullrequests.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.demo.githubpullrequests.ViewModelProviderFactory
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

/**
 * @author burhan ud din ---> Base class utility class to reduce boiler plate code for multiple screens
 */
abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: VM

    private lateinit var viewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
        setOnClickListeners()
        setUiComponents()
        Timber.plant(Timber.DebugTree())
    }

    /** @author Burhan ud din ---> returns the layout file to render in onCreate*/
    abstract fun getLayoutId(): Int

    /** @author Burhan ud din ---> returns your view model holding data */
    abstract fun getMyViewModel(): VM

    /** @author Burhan ud din ---> override this method to initialize your views*/
    open fun setUiComponents() {}

    /** @author Burhan ud din ---> set your onClickListeners by overriding this utility method*/
    open fun setOnClickListeners() {}

    /** @author Burhan ud din ---> return the views present in the layout*/
    fun getViewBinding(): VB {
        return viewBinding
    }

    private fun performDataBinding() {
        viewBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = getMyViewModel()
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

}