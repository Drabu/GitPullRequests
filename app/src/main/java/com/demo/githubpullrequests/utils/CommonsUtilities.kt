package com.demo.githubpullrequests.utils

import android.content.Context
import android.net.ConnectivityManager

object CommonsUtilities {

    fun isConnectedToNetwork(mContext: Context): Boolean {
        val networkInfo =  (mContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager).activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }

}