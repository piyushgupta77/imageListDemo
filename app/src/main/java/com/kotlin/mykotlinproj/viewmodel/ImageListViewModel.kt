package com.kotlin.mykotlinproj.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.mykotlinproj.Constants
import com.kotlin.mykotlinproj.data.model.images.SearchResponse
import com.kotlin.mykotlinproj.data.model.images.UnsplashPhoto
import com.kotlin.mykotlinproj.data.repo.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    private val TAG: String? = ImageListViewModel::class.java.canonicalName

    private val pageNumber: Int = 0
    val isDataLoading = MutableLiveData<Boolean>().apply { value = false }
    val isEmptyData = MutableLiveData<Boolean>().apply { value = true }
    val imageSearchResultItems = MutableLiveData<List<UnsplashPhoto>>()
    val error = MutableLiveData<Throwable>()
    val newSearch = MutableLiveData<Boolean>()
    private var mSearchQuery: String = ""

    fun searchPhotos(searchQuery: String) {
        isDataLoading.value = true
        GlobalScope.launch(exceptionHandler) {
            val searchResponse: SearchResponse? =
                searchRepository.searchPhotos(searchQuery, pageNumber, Constants.PAGE_SIZE)
            isDataLoading.postValue(false)
            if (searchResponse != null && !searchResponse.results.isNullOrEmpty()) {
                if (!mSearchQuery.contentEquals(searchQuery)) {
                    newSearch.postValue(true)
                    mSearchQuery = searchQuery
                }
                imageSearchResultItems.postValue(searchResponse.results)
                isEmptyData.postValue(false)
            } else {
                isEmptyData.postValue(true)
            }
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { context, exception ->
        error.value = exception
    }
}