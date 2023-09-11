package com.ahmedadeltito.ui.home.postdetails.di

import com.ahmedadeltito.feature.comment.usecase.GetCommentsUseCase
import com.ahmedadeltito.feature.post.usecase.GetPostUseCase
import com.ahmedadeltito.ui.home.mapper.CommentEntityToUiMapper
import com.ahmedadeltito.ui.home.mapper.PostEntityToUiMapper
import com.ahmedadeltito.ui.home.postdetails.viewmodel.PostDetailsViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Dagger Module that provides [PostDetailsViewModelFactory].
 */
@Module
class PostDetailsFragmentModule {

    @Provides
    @PostDetailsFragmentScope
    fun providerPostMapper(): PostEntityToUiMapper = PostEntityToUiMapper()

    @Provides
    @PostDetailsFragmentScope
    fun providerCommentMapper(): CommentEntityToUiMapper = CommentEntityToUiMapper()

    @Provides
    @PostDetailsFragmentScope
    fun providerViewModelFactory(
        getPostUseCase: GetPostUseCase,
        getCommentsUseCase: GetCommentsUseCase,
        postEntityToUiMapper: PostEntityToUiMapper,
        commentEntityToUiMapper: CommentEntityToUiMapper
    ): PostDetailsViewModelFactory = PostDetailsViewModelFactory(
        getPostUseCase = getPostUseCase,
        getCommentsUseCase = getCommentsUseCase,
        postEntityToUiMapper = postEntityToUiMapper,
        commentEntityToUiMapper = commentEntityToUiMapper
    )
}