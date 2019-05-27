package com.demo.githubpullrequests.utils

object AppConstants {
    const val DEFAULT_ITEM_COUNT = 0
    const val PAGE_SIZE = "20"
    const val FIRST_PAGE = 1
    const val BASE_URL = "https://api.github.com/"

    const val PROGRESS_VIEW = 1
    const val LIST_VIEW = 12

    enum class LoadingManager {
        STATE_NO_MORE_PAGES,
        STATE_REFRESHING,
        STATE_COMPLETED,
        STATE_ERROR,
        STATE_NO_INTERNET,
        STATE_FORCE_REFRESH
    }

    class PagingManager(
        var currentItems: Int = AppConstants.DEFAULT_ITEM_COUNT,
        var scrolledOutItems: Int = AppConstants.DEFAULT_ITEM_COUNT,
        var totalItems: Int = AppConstants.DEFAULT_ITEM_COUNT,
        var isScrolling: Boolean = false,
        var pageNumber: Int = AppConstants.FIRST_PAGE
    )

}

