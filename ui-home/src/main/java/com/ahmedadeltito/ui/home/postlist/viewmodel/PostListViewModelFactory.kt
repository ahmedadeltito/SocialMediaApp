package com.ahmedadeltito.ui.home.postlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahmedadeltito.feature.post.usecase.GetPostsUseCase
import com.ahmedadeltito.ui.home.mapper.PostEntityToUiMapper

/**
 * ViewModel Factory class for building [PostListViewModel]
 */
class PostListViewModelFactory(
    private val getPostsUseCase: GetPostsUseCase,
    private val postEntityToUiMapper: PostEntityToUiMapper,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = PostListViewModel(
        getPostsUseCase = getPostsUseCase,
        postEntityToUiMapper = postEntityToUiMapper
    ) as T

}