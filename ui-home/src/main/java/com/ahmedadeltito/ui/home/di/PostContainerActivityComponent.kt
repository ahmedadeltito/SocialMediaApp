package com.ahmedadeltito.ui.home.di

import com.ahmedadeltito.ui.home.PostContainerActivity
import dagger.Component

/**
 * Dagger Component that creates [PostContainerActivityModule] with the help of [PostContainerActivityComponent].
 */
@PostContainerActivityScope
@Component(
    modules = [
        PostContainerActivityModule::class
    ]
)
interface PostContainerActivityComponent {

    fun inject(activity: PostContainerActivity)

    @Component.Factory
    interface Factory {
        fun create(): PostContainerActivityComponent
    }
}