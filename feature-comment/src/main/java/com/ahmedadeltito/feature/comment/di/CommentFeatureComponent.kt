package com.ahmedadeltito.feature.comment.di

import com.ahmedadeltito.datasource.di.DataSourceComponent
import com.ahmedadeltito.feature.comment.usecase.GetCommentsUseCase
import dagger.Component

/**
 * Dagger Component that creates [CommentFeatureModule] with the help of [DataSourceComponent].
 */
@CommentFeatureScope
@Component(
    modules = [
        CommentFeatureModule::class
    ],
    dependencies = [
        DataSourceComponent::class
    ]
)
interface CommentFeatureComponent {

    fun getCommentsUseCase(): GetCommentsUseCase

    @Component.Factory
    interface Factory {
        fun create(dataSourceComponent: DataSourceComponent): CommentFeatureComponent
    }
}