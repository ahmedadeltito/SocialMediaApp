package com.ahmedadeltito.ui.home.postlist.udf

import com.ahmedadeltito.core.udf.UiActionEvent
import com.ahmedadeltito.core.udf.UiSideEffect
import com.ahmedadeltito.core.udf.UiViewState
import com.ahmedadeltito.ui.home.model.PostUiModel
import com.ahmedadeltito.ui.home.postlist.fragment.PostListFragment

/**
 * Simple Unidirectional Dataflow layer for handling actions, view states and side effects of
 * [PostListFragment]
 */
class PostListUDF {

    sealed class ActionEvent : UiActionEvent {
        data object OnFetchPostList : ActionEvent()
        data object OnSwipeToRefresh : ActionEvent()
        data class OnPostItemClicked(val postId: String) : ActionEvent()
    }

    data class ViewState(
        val isLoading: Boolean = false,
        val postList: List<PostUiModel> = emptyList(),
        val throwable: Throwable? = null,
    ) : UiViewState

    sealed class SideEffect : UiSideEffect {
        data class NavigateToPostDetails(val postId: String) : SideEffect()
    }
}