package com.ahmedadeltito.feature.post.di

import com.ahmedadeltito.datasource.di.DataSourceComponent
import com.ahmedadeltito.feature.post.usecase.GetPostUseCase
import com.ahmedadeltito.feature.post.usecase.GetPostsUseCase
import dagger.Component

/**
 * Dagger Component that creates [PostFeatureModule] with the help of [DataSourceComponent].
 */
@PostFeatureScope
@Component(
    modules = [
        PostFeatureModule::class
    ],
    dependencies = [
        DataSourceComponent::class
    ]
)
interface PostFeatureComponent {

    fun getPostsUseCase(): GetPostsUseCase
    fun getPostUseCase(): GetPostUseCase

    @Component.Factory
    interface Factory {
        fun create(dataSourceComponent: DataSourceComponent): PostFeatureComponent
    }
}