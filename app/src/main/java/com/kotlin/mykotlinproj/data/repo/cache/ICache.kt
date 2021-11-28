package com.kotlin.mykotlinproj.data.repo.cache

import com.kotlin.mykotlinproj.data.model.images.SearchResponse
import com.kotlin.mykotlinproj.data.model.images.UnsplashPhoto

interface ICache {
    fun saveResult(
        searchQuery: String,
        pageNumber: Int,
        pageSize: Int,
        searchResponse: SearchResponse
    )
    fun getSavedResult(query: String, pageNumber: Int, pageSize: Int): ArrayList<UnsplashPhoto>
}