package com.kotlin.mykotlinproj.data.repo.cache

import com.kotlin.mykotlinproj.data.model.images.SearchResponse
import com.kotlin.mykotlinproj.data.model.images.UnsplashPhoto

class Cache {

    private val inMemCache = InMemCache()

    fun saveResult(
        searchQuery: String,
        pageNumber: Int,
        pageSize: Int,
        searchResponse: SearchResponse
    ) {
        inMemCache.saveResult(searchQuery, pageNumber, pageSize, searchResponse)
    }

    fun getSavedResult(
        query: String, pageNumber: Int,
        pageSize: Int
    ): ArrayList<UnsplashPhoto> {
        return inMemCache.getSavedResult(query, pageNumber, pageSize)
    }
}