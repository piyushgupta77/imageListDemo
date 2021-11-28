package com.kotlin.mykotlinproj.data.model.images

data class SearchResponse(
    val total: Int,
    val total_pages: Int,
    val results: List<UnsplashPhoto>
)