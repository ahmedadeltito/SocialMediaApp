package com.ahmedadeltito.ui.home.verifier

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.ahmedadeltito.ui.home.page.PostListPage
import com.ahmedadeltito.ui.home.postlist.fragment.PostListFragment

/**
 * An object that verifies [PostListFragment] behaviour.
 */
object PostListVerifier {

    /**
     * Executes asserts within the scope of the [PostListVerifier].
     */
    operator fun invoke(action: PostListVerifier.() -> Unit) {
        action(PostListVerifier)
    }

    /**
     * Verifies that the [PostListPage.postListRecyclerView] of the [PostListFragment] is visible.
     */
    fun verifyPostListDisplayed() {
        onView(PostListPage.postListRecyclerView).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}