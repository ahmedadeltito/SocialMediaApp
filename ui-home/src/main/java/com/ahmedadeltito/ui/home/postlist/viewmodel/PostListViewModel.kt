package com.ahmedadeltito.ui.home.postlist.viewmodel

import androidx.lifecycle.viewModelScope
import com.ahmedadeltito.core.vm.BaseViewModel
import com.ahmedadeltito.feature.post.usecase.GetPostsUseCase
import com.ahmedadeltito.ui.home.mapper.PostEntityToUiMapper
import com.ahmedadeltito.ui.home.postlist.udf.PostListUDF
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * View model for [GetPostsUseCase]
 */
class PostListViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val postEntityToUiMapper: PostEntityToUiMapper,
) : BaseViewModel<PostListUDF.ActionEvent, PostListUDF.ViewState, PostListUDF.SideEffect>() {

    override fun createInitialState(): PostListUDF.ViewState = PostListUDF.ViewState()

    override fun handleActionEvents(actionEvent: PostListUDF.ActionEvent) {
        when (actionEvent) {
            is PostListUDF.ActionEvent.OnFetchPostList ->
                fetchPostList()

            is PostListUDF.ActionEvent.OnSwipeToRefresh ->
                fetchPostList()

            is PostListUDF.ActionEvent.OnPostItemClicked ->
                emitSideEffect { PostListUDF.SideEffect.NavigateToPostDetails(postId = actionEvent.postId) }
        }
    }

    private fun fetchPostList() {
        viewModelScope.launch {
            getPostsUseCase.invoke()
                .onStart { emitViewState { copy(isLoading = true, throwable = null) } }
                .onCompletion { emitViewState { copy(isLoading = false, throwable = null) } }
                .catch { throwable -> emitViewState { copy(throwable = handleThrowable(throwable)) } }
                .collect { postListEntity ->
                    val newPostListUiModel = postListEntity.map { postEntity ->
                        postEntityToUiMapper.map(input = postEntity)
                    }
                    emitViewState { copy(postList = newPostListUiModel, throwable = null) }
                }
        }
    }
}