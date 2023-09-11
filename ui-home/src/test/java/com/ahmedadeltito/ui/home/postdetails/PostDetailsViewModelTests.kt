package com.ahmedadeltito.ui.home.postdetails

import com.ahmedadeltito.core.exceptions.NetworkException
import com.ahmedadeltito.feature.comment.usecase.GetCommentsUseCase
import com.ahmedadeltito.feature.post.usecase.GetPostUseCase
import com.ahmedadeltito.ui.home.CoroutinesTestRule
import com.ahmedadeltito.ui.home.MockingData
import com.ahmedadeltito.ui.home.mapper.CommentEntityToUiMapper
import com.ahmedadeltito.ui.home.mapper.PostEntityToUiMapper
import com.ahmedadeltito.ui.home.postdetails.udf.PostDetailsUDF
import com.ahmedadeltito.ui.home.postdetails.viewmodel.PostDetailsViewModel
import com.google.common.truth.Truth
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class PostDetailsViewModelTests {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val postEntityToUiMapper = PostEntityToUiMapper()
    private val commentEntityToUiMapper = CommentEntityToUiMapper()

    private val getPostUseCase = mockk<GetPostUseCase>()
    private val getCommentsUseCase = mockk<GetCommentsUseCase>()

    @Test
    fun `emit initial view state scenario`() = runTest {
        val postDetailsViewModel = PostDetailsViewModel(
            getPostUseCase = getPostUseCase,
            getCommentsUseCase = getCommentsUseCase,
            postEntityToUiMapper = postEntityToUiMapper,
            commentEntityToUiMapper = commentEntityToUiMapper
        )

        /**
         * GIVEN Initial State is called
         */
        postDetailsViewModel.emitViewState {
            copy(post = MockingData.emptyPost)
        }

        /**
         * THEN [PostDetailsUDF.ViewState.post] is [MockingData.emptyPost]
         */
        Truth.assertThat(postDetailsViewModel.uiViewState.value).isEqualTo(
            PostDetailsUDF.ViewState(post = MockingData.emptyPost)
        )
    }

    @Test
    fun `emit success view state scenario for post`() = runTest {
        val postDetailsViewModel = PostDetailsViewModel(
            getPostUseCase = getPostUseCase,
            getCommentsUseCase = getCommentsUseCase,
            postEntityToUiMapper = postEntityToUiMapper,
            commentEntityToUiMapper = commentEntityToUiMapper
        )

        /**
         * GIVEN Success State is called
         */
        postDetailsViewModel.emitViewState {
            copy(post = MockingData.post)
        }

        /**
         * THEN [PostDetailsUDF.ViewState.post] is [MockingData.post]
         */
        Truth.assertThat(postDetailsViewModel.uiViewState.value).isEqualTo(
            PostDetailsUDF.ViewState(post = MockingData.post)
        )
    }

    @Test
    fun `emit success view state scenario for list of comments`() = runTest {
        val postDetailsViewModel = PostDetailsViewModel(
            getPostUseCase = getPostUseCase,
            getCommentsUseCase = getCommentsUseCase,
            postEntityToUiMapper = postEntityToUiMapper,
            commentEntityToUiMapper = commentEntityToUiMapper
        )

        /**
         * GIVEN Success State is called
         */
        postDetailsViewModel.emitViewState {
            copy(commentList = MockingData.commentList)
        }

        /**
         * THEN [PostDetailsUDF.ViewState.post] is [MockingData.commentList]
         */
        Truth.assertThat(postDetailsViewModel.uiViewState.value).isEqualTo(
            PostDetailsUDF.ViewState(commentList = MockingData.commentList)
        )
    }

    @Test
    fun `emit success view state scenario for post and list of comments`() = runTest {
        val postDetailsViewModel = PostDetailsViewModel(
            getPostUseCase = getPostUseCase,
            getCommentsUseCase = getCommentsUseCase,
            postEntityToUiMapper = postEntityToUiMapper,
            commentEntityToUiMapper = commentEntityToUiMapper
        )

        /**
         * GIVEN Success State is called
         */
        postDetailsViewModel.emitViewState {
            copy(
                post = MockingData.post,
                commentList = MockingData.commentList
            )
        }

        /**
         * THEN [PostDetailsUDF.ViewState.post] is [MockingData.commentList]
         */
        Truth.assertThat(postDetailsViewModel.uiViewState.value).isEqualTo(
            PostDetailsUDF.ViewState(
                post = MockingData.post,
                commentList = MockingData.commentList
            )
        )
    }

    @Test
    fun `emit error view state scenario`() = runTest {
        val postDetailsViewModel = PostDetailsViewModel(
            getPostUseCase = getPostUseCase,
            getCommentsUseCase = getCommentsUseCase,
            postEntityToUiMapper = postEntityToUiMapper,
            commentEntityToUiMapper = commentEntityToUiMapper
        )
        val throwable = NetworkException.NotFoundException(notFound = "No Internet Connection")

        /**
         * GIVEN Success State is called
         */
        postDetailsViewModel.emitViewState {
            copy(throwable = throwable)
        }

        /**
         * THEN [PostDetailsUDF.ViewState.throwable] is [NetworkException.NotFoundException]
         */
        Truth.assertThat(postDetailsViewModel.uiViewState.value).isEqualTo(
            PostDetailsUDF.ViewState(throwable = throwable)
        )
    }
}