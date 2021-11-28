package com.kotlin.mykotlinproj.data.network

import retrofit2.Retrofit
import javax.inject.Inject

class NetworkClient @Inject constructor(private val retrofit: Retrofit) {
    fun <T> create(api: Class<T>): T {
        return retrofit.create(api)
    }
}