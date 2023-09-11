package com.ahmedadeltito.ui.home.di

import com.ahmedadeltito.ui.home.coordinator.PostNavigator
import dagger.Module
import dagger.Provides

/**
 * Dagger Module that provides [PostNavigator].
 */
@Module
class PostContainerActivityModule {

    @Provides
    @PostContainerActivityScope
    fun provideNavigator(): PostNavigator = PostNavigator()
}