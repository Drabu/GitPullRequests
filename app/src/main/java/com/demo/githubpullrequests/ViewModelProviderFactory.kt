package com.demo.githubpullrequests

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.githubpullrequests.data.model.view.GithubViewModel
import com.demo.githubpullrequests.data.repositories.GithubRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelProviderFactory @Inject constructor(
    var application: Application,
    var githubRepository: GithubRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GithubViewModel(application, githubRepository) as T
    }
}