package com.ahmedadeltito.ui.home.postdetails.viewmodel

import androidx.lifecycle.viewModelScope
import com.ahmedadeltito.core.vm.BaseViewModel
import com.ahmedadeltito.feature.comment.usecase.GetCommentsUseCase
import com.ahmedadeltito.feature.post.usecase.GetPostUseCase
import com.ahmedadeltito.ui.home.mapper.CommentEntityToUiMapper
import com.ahmedadeltito.ui.home.mapper.PostEntityToUiMapper
import com.ahmedadeltito.ui.home.postdetails.udf.PostDetailsUDF
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * View model for [GetPostUseCase] and [GetCommentsUseCase]
 */
class PostDetailsViewModel(
    private val getPostUseCase: GetPostUseCase,
    private val getCommentsUseCase: GetCommentsUseCase,
    private val postEntityToUiMapper: PostEntityToUiMapper,
    private val commentEntityToUiMapper: CommentEntityToUiMapper
) : BaseViewModel<PostDetailsUDF.ActionEvent, PostDetailsUDF.ViewState, PostDetailsUDF.SideEffect>() {

    override fun createInitialState(): PostDetailsUDF.ViewState = PostDetailsUDF.ViewState()

    override fun handleActionEvents(actionEvent: PostDetailsUDF.ActionEvent) {
        when (actionEvent) {
            is PostDetailsUDF.ActionEvent.OnFetchPost -> fetchPost(postId = actionEvent.postId)
            is PostDetailsUDF.ActionEvent.OnFetchCommentList -> fetchComment(postId = actionEvent.postId)
        }
    }

    private fun fetchPost(postId: String) {
        viewModelScope.launch {
            getPostUseCase.invoke(id = postId)
                .onStart { emitViewState { copy(isPostLoading = true, throwable = null) } }
                .onCompletion { emitViewState { copy(isPostLoading = false, throwable = null) } }
                .catch { throwable -> emitViewState { copy(throwable = handleThrowable(throwable)) } }
                .collect { postEntity ->
                    val postUiModel = postEntityToUiMapper.map(input = postEntity)
                    emitViewState { copy(post = postUiModel, throwable = null) }
                }
        }
    }

    private fun fetchComment(postId: String) {
        viewModelScope.launch {
            getCommentsUseCase.invoke(postId = postId)
                .onStart { emitViewState { copy(isCommentListLoading = true, throwable = null) } }
                .onCompletion {
                    emitViewState {
                        copy(
                            isCommentListLoading = false,
                            throwable = null
                        )
                    }
                }
                .catch { throwable -> emitViewState { copy(throwable = handleThrowable(throwable)) } }
                .collect { commentListEntity ->
                    val commentListUiModel = commentListEntity.map {
                        commentEntityToUiMapper.map(input = it)
                    }
                    emitViewState { copy(commentList = commentListUiModel, throwable = null) }
                }
        }
    }
}