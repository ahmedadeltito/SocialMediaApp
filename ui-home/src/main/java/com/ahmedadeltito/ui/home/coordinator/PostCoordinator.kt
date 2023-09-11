package com.ahmedadeltito.ui.home.coordinator

import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

/**
 * Coordinator for post screen that may handles more than noe navigator.
 */
class PostCoordinator
@Inject
constructor(private val navigator: PostNavigator) {

    fun startPostList(activity: AppCompatActivity) {
        navigator.navigateToPostListFragment(activity = activity)
    }

    fun startPostDetails(activity: AppCompatActivity, postId: String) {
        navigator.navigateToPostDetailsFragment(activity = activity, postId = postId)
    }
}