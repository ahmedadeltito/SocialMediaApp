package com.ahmedadeltito.ui.home.postdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahmedadeltito.feature.comment.usecase.GetCommentsUseCase
import com.ahmedadeltito.feature.post.usecase.GetPostUseCase
import com.ahmedadeltito.ui.home.mapper.CommentEntityToUiMapper
import com.ahmedadeltito.ui.home.mapper.PostEntityToUiMapper

/**
 * ViewModel Factory class for building [PostDetailsViewModel]
 */
class PostDetailsViewModelFactory(
    private val getPostUseCase: GetPostUseCase,
    private val getCommentsUseCase: GetCommentsUseCase,
    private val postEntityToUiMapper: PostEntityToUiMapper,
    private val commentEntityToUiMapper: CommentEntityToUiMapper
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        PostDetailsViewModel(
            getPostUseCase = getPostUseCase,
            getCommentsUseCase = getCommentsUseCase,
            postEntityToUiMapper = postEntityToUiMapper,
            commentEntityToUiMapper = commentEntityToUiMapper
        ) as T
}