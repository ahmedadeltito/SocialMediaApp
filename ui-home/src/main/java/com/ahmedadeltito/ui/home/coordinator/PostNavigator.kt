package com.ahmedadeltito.ui.home.coordinator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmedadeltito.core.extensions.replaceFragment
import com.ahmedadeltito.ui.home.R
import com.ahmedadeltito.ui.home.postdetails.fragment.PostDetailsFragment
import com.ahmedadeltito.ui.home.postlist.fragment.PostListFragment

/**
 * Navigator for post screen that navigates from the caller class to the destination method.
 */
class PostNavigator {

    fun navigateToPostListFragment(activity: AppCompatActivity) {
        activity.replaceFragment(
            fragment = PostListFragment.newInstance(),
            containerViewId = R.id.activity_post_container
        )
    }

    fun navigateToPostDetailsFragment(activity: AppCompatActivity, postId: String) {
        val bundle = Bundle().apply {
            putString(PostDetailsFragment.POST_ID_KEY, postId)
        }
        activity.replaceFragment(
            fragment = PostDetailsFragment.newInstance(args = bundle),
            containerViewId = R.id.activity_post_container,
            enterAnimation = R.anim.enter_from_right,
            exitAnimation = R.anim.exit_to_left,
            popEnterAnimation = R.anim.enter_from_left,
            popExitAnimation = R.anim.exit_to_right
        )
    }
}