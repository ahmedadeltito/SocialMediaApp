package com.ahmedadeltito.ui.home.postlist

import com.ahmedadeltito.core.exceptions.NetworkException
import com.ahmedadeltito.feature.post.usecase.GetPostsUseCase
import com.ahmedadeltito.ui.home.CoroutinesTestRule
import com.ahmedadeltito.ui.home.MockingData
import com.ahmedadeltito.ui.home.mapper.PostEntityToUiMapper
import com.ahmedadeltito.ui.home.postlist.udf.PostListUDF
import com.ahmedadeltito.ui.home.postlist.viewmodel.PostListViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class PostListViewModelTests {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val postEntityToUiMapper = PostEntityToUiMapper()

    private val getPostUseCase = mockk<GetPostsUseCase>()

    @Test
    fun `emit initial view state scenario`() = runTest {
        val postListViewModel = PostListViewModel(
            getPostsUseCase = getPostUseCase,
            postEntityToUiMapper = postEntityToUiMapper
        )
        /**
         * GIVEN Initial State is called
         */
        postListViewModel.emitViewState {
            copy(postList = emptyList())
        }

        /**
         * THEN [PostListUDF.ViewState.postList] is [emptyList]
         */
        assertThat(postListViewModel.uiViewState.value).isEqualTo(
            PostListUDF.ViewState(postList = emptyList())
        )
    }

    @Test
    fun `emit success view state scenario`() = runTest {
        val postListViewModel = PostListViewModel(
            getPostsUseCase = getPostUseCase,
            postEntityToUiMapper = postEntityToUiMapper
        )
        /**
         * GIVEN Success State is called
         */
        postListViewModel.emitViewState {
            copy(postList = MockingData.postList)
        }

        /**
         * THEN [PostListUDF.ViewState.postList] is [MockingData.postList]
         */
        assertThat(postListViewModel.uiViewState.value).isEqualTo(
            PostListUDF.ViewState(postList = MockingData.postList)
        )
    }

    @Test
    fun `emit error view state scenario`() = runTest {
        val postListViewModel = PostListViewModel(
            getPostsUseCase = getPostUseCase, postEntityToUiMapper = postEntityToUiMapper
        )
        val throwable = NetworkException.NotFoundException(notFound = "No Internet Connection")

        /**
         * GIVEN Success State is called
         */
        postListViewModel.emitViewState {
            copy(
                isLoading = false,
                postList = emptyList(),
                throwable = throwable
            )
        }

        /**
         * THEN [PostListUDF.ViewState.throwable] is [NetworkException.NotFoundException]
         */
        assertThat(postListViewModel.uiViewState.value).isEqualTo(
            PostListUDF.ViewState(
                isLoading = false,
                postList = emptyList(),
                throwable = throwable
            )
        )
    }
}