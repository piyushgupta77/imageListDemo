package com.kotlin.mykotlinproj

class Constants {
    companion object {

        val DEBOUNCE_TEXT_CHANGE_DURATION = 250L
        const val BASE_URL = "https://api.unsplash.com/"
        const val PAGE_SIZE = 20
        const val CACHE_SIZE = 50 * 1024 * 1024 // 100 MB
        const val CACHE_SIZE_API_RESPONSE = 10 * 1024 // 1 MB
        const val SELECTED_IMAGE: String = "SELECTED_IMAGE"
    }
}