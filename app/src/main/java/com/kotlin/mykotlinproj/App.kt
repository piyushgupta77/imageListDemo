package com.kotlin.mykotlinproj

import android.app.Application
import com.squareup.picasso.LruCache
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        initPicassoSingleton()
    }

    private fun initPicassoSingleton() {
        val builder = Picasso.Builder(this)
        builder.downloader(OkHttp3Downloader(this, Long.MAX_VALUE))
        builder.memoryCache(LruCache(Constants.CACHE_SIZE))
        val picassoInstance = builder.build()
//        picassoInstance.setIndicatorsEnabled(true)
//        picassoInstance.isLoggingEnabled = true
        Picasso.setSingletonInstance(picassoInstance)
    }
}