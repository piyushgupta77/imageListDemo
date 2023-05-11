package com.kotlin.mykotlinproj.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.kotlin.mykotlinproj.Constants
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.io.IOException


class CustomOkHttpBuilder {

    companion object {

        private val TAG = CustomOkHttpBuilder::class.java.canonicalName

        const val HEADER_CACHE_CONTROL = "Cache-Control"
        const val HEADER_PRAGMA = "Pragma"

        fun getOkHttpClient(context: Context): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            val cache = Cache(
                File(context.cacheDir, "http-cache"),
                Constants.CACHE_SIZE_API_RESPONSE.toLong()
            )

            val builder = OkHttpClient.Builder()
                .addInterceptor(object : Interceptor {
                    @Throws(IOException::class)
                    override fun intercept(chain: Interceptor.Chain): Response {
                        var request: Request = chain.request()
                        if (!isConnected(context)) {
                            val maxStale = 60 * 60 * 24 * 30 // Offline cache available for 30 days
                            request = request.newBuilder()
                                .header(
                                    HEADER_CACHE_CONTROL,
                                    "public, only-if-cached, max-stale=$maxStale"
                                )
                                .removeHeader("Pragma")
                                .build()
                        }
                        return chain.proceed(request)
                    }
                })
                .addNetworkInterceptor(object : Interceptor {
                    @Throws(IOException::class)
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val response: Response = chain.proceed(chain.request())
                        val maxAge =
                            1 * 24 * 60 * 60 // read from cache for 1 day even if there is internet connection
                        return response.newBuilder()
                            .header(HEADER_CACHE_CONTROL, "public, max-age=$maxAge")
                            .removeHeader(HEADER_PRAGMA)
                            .build()
                    }
                })
                .addInterceptor(logging)
                .cache(cache)
            return builder.build()
        }

        fun isConnected(context: Context): Boolean {
            try {
                val e = context.getSystemService(
                    Context.CONNECTIVITY_SERVICE
                ) as ConnectivityManager
                val activeNetwork = e.activeNetworkInfo
                return activeNetwork != null && activeNetwork.isConnectedOrConnecting
            } catch (e: Exception) {
                Log.w(TAG, e.toString())
            }
            return false
        }
    }
}