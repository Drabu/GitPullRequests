package com.demo.githubpullrequests.utils

object AppConstants {
    var BASE_URL = "https://api.github.com/"


    enum class RequestStatus {
        STATE_REFRESHING,
        STATE_COMPLETED,
        STATE_ERROR,
        STATE_NO_INTERNET,
        STATE_FORCE_REFRESH
    }
}

