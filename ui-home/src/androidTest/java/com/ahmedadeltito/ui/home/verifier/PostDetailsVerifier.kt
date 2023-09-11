package com.ahmedadeltito.ui.home.verifier

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.ahmedadeltito.ui.home.page.PostDetailsPage
import com.ahmedadeltito.ui.home.postdetails.fragment.PostDetailsFragment

/**
 * An object that verifies [PostDetailsFragment] behaviour.
 */
object PostDetailsVerifier {

    /**
     * Executes asserts within the scope of the [PostDetailsVerifier].
     */
    operator fun invoke(action: PostDetailsVerifier.() -> Unit) {
        action(PostDetailsVerifier)
    }

    /**
     * Verifies that the [PostDetailsPage.postDetailsTitleTv] of the [PostDetailsFragment] has [postTitle].
     */
    fun verifyPostTitle(postTitle: String) {
        onView(PostDetailsPage.postDetailsTitleTv).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    postTitle
                )
            )
        )
    }

    /**
     * Verifies that the [PostDetailsPage.postDetailsBodyTv] of the [PostDetailsFragment] has [postBody].
     */
    fun verifyPostBody(postBody: String) {
        onView(PostDetailsPage.postDetailsBodyTv).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    postBody
                )
            )
        )
    }

    /**
     * Verifies that the [PostDetailsPage.postDetailsTitleTv] of the [PostDetailsFragment] is visible.
     */
    fun verifyPostTitleIsDisplayed() {
        onView(PostDetailsPage.postDetailsTitleTv).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    /**
     * Verifies that the [PostDetailsPage.postDetailsBodyTv] of the [PostDetailsFragment] is visible.
     */
    fun verifyPostDetailsIsDisplayed() {
        onView(PostDetailsPage.postDetailsBodyTv).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}