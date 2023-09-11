package com.ahmedadeltito.ui.home.page

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers
import com.ahmedadeltito.ui.home.postlist.view.PostListView
import org.hamcrest.Matcher

/**
 * A Page for the [PostListView] view.
 */
object PostListPage {

    /**
     * Matcher for the view with the [PostListView]'s recycler view.
     */
    val postListRecyclerView: Matcher<View>
        get() = ViewMatchers.withId(com.ahmedadeltito.ui.home.R.id.post_list_rv)
}