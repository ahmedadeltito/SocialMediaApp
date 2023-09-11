package com.ahmedadeltito.ui.home.postlist.di

import com.ahmedadeltito.feature.post.usecase.GetPostsUseCase
import com.ahmedadeltito.ui.home.coordinator.PostNavigator
import com.ahmedadeltito.ui.home.mapper.PostEntityToUiMapper
import com.ahmedadeltito.ui.home.postlist.adapter.PostListAdapter
import com.ahmedadeltito.ui.home.postlist.viewmodel.PostListViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Dagger Module that provides [PostListViewModelFactory] and [PostNavigator].
 */
@Module
class PostListFragmentModule {

    @Provides
    @PostListFragmentScope
    fun providerMapper(): PostEntityToUiMapper = PostEntityToUiMapper()

    @Provides
    @PostListFragmentScope
    fun providerViewModelFactory(
        getPostsUseCase: GetPostsUseCase,
        postEntityToUiMapper: PostEntityToUiMapper
    ): PostListViewModelFactory = PostListViewModelFactory(
        getPostsUseCase = getPostsUseCase,
        postEntityToUiMapper = postEntityToUiMapper
    )

    @Provides
    @PostListFragmentScope
    fun provideAdapter(): PostListAdapter = PostListAdapter()

    @Provides
    @PostListFragmentScope
    fun provideNavigator(): PostNavigator = PostNavigator()
}