package com.demo.githubpullrequests.ui.console

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.demo.githubpullrequests.R
import com.demo.githubpullrequests.data.model.view.BaseViewModel
import com.demo.githubpullrequests.databinding.ActivityUserRepoSelectionBinding
import com.demo.githubpullrequests.ui.base.BaseActivity

/**
 *@author Burhan ud din ---> Activity used to write repository and profile
 */
class UserRepoSelection : BaseActivity<ActivityUserRepoSelectionBinding, BaseViewModel>(), View.OnClickListener {

    override fun setOnClickListeners() {
        getViewBinding().getPullRequestsBTN.setOnClickListener(this)
        getViewBinding().demoCredTV.setOnClickListener(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_user_repo_selection

    override fun getMyViewModel(): BaseViewModel = ViewModelProviders.of(this).get(BaseViewModel::class.java)

    override fun onClick(view: View?) {

        when (view?.id) {

            R.id.getPullRequestsBTN -> {
                if (getViewBinding().githubUsername.text.toString().isNotEmpty() && getViewBinding().githubRepoName.text.toString().isNotEmpty()) {
                    startActivity(
                        Intent(this, MainActivity::class.java).putExtra(
                            MainActivity.CONFIG,
                            MainActivity.Config(
                                getViewBinding().githubUsername.text.toString(),
                                getViewBinding().githubRepoName.text.toString()
                            )
                        )
                    )
                } else {
                    Toast.makeText(this, "Please fill the fields ", Toast.LENGTH_LONG).show()
                }
            }


            R.id.demoCredTV -> {

                getViewBinding().githubRepoName.setText(getString(R.string.demo_rep))
                getViewBinding().githubUsername.setText(getString(R.string.demo_username))

            }


        }
    }

}
