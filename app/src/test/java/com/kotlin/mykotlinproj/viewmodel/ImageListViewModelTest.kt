package com.kotlin.mykotlinproj.viewmodel


import com.kotlin.mykotlinproj.MainDispatcherRule
import com.kotlin.mykotlinproj.TestData
import com.kotlin.mykotlinproj.data.model.images.SearchResponse
import com.kotlin.mykotlinproj.data.model.images.UnsplashPhoto
import com.kotlin.mykotlinproj.data.repo.SearchRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ImageListViewModelTest /*: TestCase() */ {

    @get:Rule
    val rule = MainDispatcherRule()

    private val searchRepository: SearchRepository = mockk()

    lateinit var viewModel: ImageListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = ImageListViewModel(searchRepository)
    }

    @Test
    fun searchPhotos_EmptyResponseList() = runTest {
        val searchResponse = SearchResponse(0, 0, ArrayList())
        val searchQuery = "Taj mahal"
        coEvery { searchRepository.searchPhotos(searchQuery, 0, 20) }.returns(searchResponse)
        viewModel.searchPhotos(searchQuery)

        Assert.assertTrue(viewModel.isEmptyData.value!!)
    }

    @Test
    fun searchPhotos_NonEmptyResponseList() = runTest {
        val list = ArrayList<UnsplashPhoto>()
        list.add(TestData.dummyImage)
        val searchResponse = SearchResponse(0, 0, list)
        val searchQuery = "Taj mahal"
        coEvery { searchRepository.searchPhotos(searchQuery, 0, 20) }.returns(searchResponse)
        viewModel.searchPhotos(searchQuery)

        Assert.assertFalse(viewModel.isEmptyData.value!!)
        Assert.assertTrue(viewModel.newSearch.value!!)
        Assert.assertEquals(list, viewModel.imageSearchResultItems.value)
    }

    @Test
    fun searchPhotos_error() {
        val searchQuery = "Taj mahal"
        coEvery { searchRepository.searchPhotos(searchQuery, 0, 20) }.throws(Throwable())
        viewModel.searchPhotos(searchQuery)

        Assert.assertNotNull(viewModel.error.value!!)
    }
}