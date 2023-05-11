package com.kotlin.mykotlinproj.viewmodel

import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.mykotlinproj.data.model.images.UnsplashPhoto
import com.kotlin.mykotlinproj.data.repo.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageDetailViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    val isDownloading = MutableLiveData<Boolean>().apply { value = false }

    fun handleUserSaveClick(selectedImage: UnsplashPhoto, isStoragePermissionGranted: Boolean) {
        // Scoped storage, write external storage permission is not required on Android 10 and above if saving files to Downloads folder
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            if (isStoragePermissionGranted) {
                downloadFile(selectedImage)
            }
        } else {
            downloadFile(selectedImage)
        }
    }

    private fun downloadFile(selectedImage: UnsplashPhoto) {
        startDownload(selectedImage)
    }

    fun userPermissionSuccess(selectedImage: UnsplashPhoto) {
        startDownload(selectedImage)
    }

    private fun startDownload(selectedImage: UnsplashPhoto) {
        isDownloading.postValue(true)
        searchRepository.downloadFile(selectedImage)
    }
}