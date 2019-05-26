package com.demo.githubpullrequests.ui.console

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.githubpullrequests.R
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)


    }
}
