package com.kotlin.mykotlinproj.data.network

import com.kotlin.mykotlinproj.data.model.images.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    @GET("search/photos")
    fun searchPhotos(
        @Query("client_id") clientId: String,
        @Query("query") criteria: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Call<SearchResponse>
}