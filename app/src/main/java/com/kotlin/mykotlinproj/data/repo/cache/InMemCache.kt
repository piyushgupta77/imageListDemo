package com.kotlin.mykotlinproj.data.repo.cache

import android.util.Log
import androidx.collection.LruCache
import com.kotlin.mykotlinproj.Constants
import com.kotlin.mykotlinproj.data.model.images.SearchResponse
import com.kotlin.mykotlinproj.data.model.images.UnsplashPhoto

class InMemCache : ICache {

    //    private val cache = LruCache<SaveResultKey, UnsplashPhoto>(Constants.CACHE_SIZE)
    private val cache = LruCache<String, UnsplashPhoto>(Constants.CACHE_SIZE)

    override fun saveResult(
        searchQuery: String,
        pageNumber: Int,
        pageSize: Int,
        searchResponse: SearchResponse
    ) {
        searchResponse.results.forEachIndexed { index, unsplashPhoto ->
//            cache.put(SaveResultKey(searchQuery, (pageSize * pageNumber)  + index), unsplashPhoto)
            val cacheKey = searchQuery + ((pageSize * pageNumber) + index)
            cache.put(cacheKey, unsplashPhoto)
        }
    }

    override fun getSavedResult(
        query: String,
        pageNumber: Int,
        pageSize: Int
    ): ArrayList<UnsplashPhoto> {
        val list = ArrayList<UnsplashPhoto>()
        for (i in 0 until pageSize) {
//            val aa =  cache.get(SaveResultKey(query, pageNumber + i))
            val aa = cache.get(query + (pageNumber + i))
            Log.d("TAG", "found in cache $i $aa")
            aa?.let { list.add(it) }
        }
        return list
    }
}