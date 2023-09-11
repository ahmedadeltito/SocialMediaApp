package com.ahmedadeltito.ui.home.postdetails.udf

import com.ahmedadeltito.core.udf.UiActionEvent
import com.ahmedadeltito.core.udf.UiSideEffect
import com.ahmedadeltito.core.udf.UiViewState
import com.ahmedadeltito.ui.home.model.CommentUiModel
import com.ahmedadeltito.ui.home.model.PostUiModel

/**
 * Simple Unidirectional Dataflow layer for handling actions, view states and side effects of [PostDetailsFragment]
 */
class PostDetailsUDF {

    sealed class ActionEvent : UiActionEvent {
        data class OnFetchPost(val postId: String) : ActionEvent()
        data class OnFetchCommentList(val postId: String) : ActionEvent()
    }

    data class ViewState(
        val isPostLoading: Boolean = false,
        val post: PostUiModel = PostUiModel("null", "null"),
        val isCommentListLoading: Boolean = false,
        val commentList: List<CommentUiModel> = emptyList(),
        val throwable: Throwable? = null,
    ) : UiViewState

    sealed class SideEffect : UiSideEffect
}