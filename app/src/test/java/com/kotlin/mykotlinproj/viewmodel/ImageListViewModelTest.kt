package com.kotlin.mykotlinproj.viewmodel


import com.kotlin.mykotlinproj.data.repo.SearchRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImageListViewModelTest {

    @RelaxedMockK
    lateinit var searchRepository: SearchRepository

    lateinit var viewModel: ImageListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = ImageListViewModel(searchRepository)
    }

    @Test
    fun searchPhotos() {
        val searchQuery = "Taj mahal"
        viewModel.searchPhotos(searchQuery)
    }

}