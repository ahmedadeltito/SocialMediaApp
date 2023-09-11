package com.ahmedadeltito.ui.home.postlist.di

import com.ahmedadeltito.feature.post.di.PostFeatureComponent
import com.ahmedadeltito.ui.home.postlist.fragment.PostListFragment
import dagger.Component

/**
 * Dagger Component that creates [PostListFragmentModule] with the help of [PostFeatureComponent].
 */
@PostListFragmentScope
@Component(
    modules = [
        PostListFragmentModule::class
    ],
    dependencies = [
        PostFeatureComponent::class
    ]
)
interface PostListFragmentComponent {

    fun inject(fragment: PostListFragment)

    @Component.Factory
    interface Factory {
        fun create(postFeatureComponent: PostFeatureComponent): PostListFragmentComponent
    }
}