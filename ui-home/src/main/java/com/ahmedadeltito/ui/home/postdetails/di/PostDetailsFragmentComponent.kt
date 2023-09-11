package com.ahmedadeltito.ui.home.postdetails.di

import com.ahmedadeltito.feature.comment.di.CommentFeatureComponent
import com.ahmedadeltito.feature.post.di.PostFeatureComponent
import com.ahmedadeltito.ui.home.postdetails.fragment.PostDetailsFragment
import dagger.Component

/**
 * Dagger Component that creates [PostDetailsFragmentModule] with the help of
 * [PostFeatureComponent] and [CommentFeatureComponent].
 */
@PostDetailsFragmentScope
@Component(
    modules = [
        PostDetailsFragmentModule::class
    ],
    dependencies = [
        PostFeatureComponent::class,
        CommentFeatureComponent::class
    ]
)
interface PostDetailsFragmentComponent {

    fun inject(fragment: PostDetailsFragment)

    @Component.Factory
    interface Factory {
        fun create(
            postFeatureComponent: PostFeatureComponent,
            commentFeatureComponent: CommentFeatureComponent
        ): PostDetailsFragmentComponent
    }
}