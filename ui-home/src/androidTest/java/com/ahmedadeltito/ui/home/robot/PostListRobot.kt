package com.ahmedadeltito.ui.home.robot

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import com.ahmedadeltito.ui.home.matchers.CustomMatchers
import com.ahmedadeltito.ui.home.page.PostListPage
import com.ahmedadeltito.ui.home.postlist.view.PostListView

/**
 * A Robot that interacts with the [PostListRobot].
 */
object PostListRobot {

    /**
     * Executes actions within the scope of the [PostListRobot].
     */
    operator fun invoke(action: PostListRobot.() -> Unit) {
        action(PostListRobot)
    }

    /**
     * Clicks on the first post item that appears on the [PostListView]
     */
    fun clickFirstPostItem(): PostListRobot = apply {
        onView(
            PostListPage.postListRecyclerView
        ).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, CustomMatchers.clickView())
        )
    }
}