package com.kotlin.mykotlinproj.data.repo

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import com.kotlin.mykotlinproj.data.model.images.SearchResponse
import com.kotlin.mykotlinproj.data.model.images.UnsplashPhoto
import com.kotlin.mykotlinproj.data.network.NetworkClient
import com.kotlin.mykotlinproj.data.network.UnsplashApi
import com.kotlin.mykotlinproj.data.repo.cache.Cache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val cache: Cache,
    private val networkClient: NetworkClient,
    private val clientId: String,
    private val context: Context
) {

    private val TAG = SearchRepository::class.java.canonicalName

    suspend fun searchPhotos(
        query: String,
        pageNumber: Int,
        pageSize: Int
    ): SearchResponse? {
        var searchResponse: SearchResponse? = null
        withContext(Dispatchers.IO) {
            Log.d(TAG, "inside coroutine thread " + Thread.currentThread().name)
            val cachedResult = cache.getSavedResult(query, pageNumber, pageSize)
            if (!cachedResult.isNullOrEmpty()) {
                searchResponse = SearchResponse(0, 0, cachedResult)
            } else {
                val response: Response<SearchResponse> =
                    networkClient.create(UnsplashApi::class.java)
                        .searchPhotos(clientId, query, pageNumber, pageSize).execute()
                if (response.isSuccessful) {
                    searchResponse = response.body()
                    searchResponse?.let { cache.saveResult(query, pageNumber, pageSize, it) }
                }
            }
        }
        return searchResponse
    }

    fun downloadFile(selectedImage: UnsplashPhoto) {
        val url = selectedImage.urls.small
        val fileName = "happy_image.jpeg"

        val request = DownloadManager.Request(Uri.parse(url)) //url=The download url of file

        request.setMimeType("image/jpeg")
        request.setDescription("Downloading selected image") //Description

        request.setTitle(fileName) //pdfFileName=String Name of Pdf file

        request.allowScanningByMediaScanner()
        request.setAllowedOverMetered(true)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
//                "/dummy_images" /*Custom directory name below api 29*/,
                fileName
            )
        } else {
            //Higher then or equal api-29
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "/$fileName"
            )
        }
        val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(request)
    }

}