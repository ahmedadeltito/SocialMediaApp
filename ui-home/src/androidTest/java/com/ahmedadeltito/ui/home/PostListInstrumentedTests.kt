package com.ahmedadeltito.ui.home

import androidx.test.core.app.ActivityScenario
import com.ahmedadeltito.ui.home.postlist.fragment.PostListFragment
import com.ahmedadeltito.ui.home.robot.PostListRobot
import com.ahmedadeltito.ui.home.verifier.PostDetailsVerifier
import com.ahmedadeltito.ui.home.verifier.PostListVerifier
import org.junit.Test

/**
 * Instrumented tests, which will execute on an Android device to test
 * the integration of [PostListFragment].
 */
class PostListInstrumentedTests {

    private companion object {
        const val POST_LIST_REQUEST_DELAY = 3000L
    }

    @Test
    fun showPostListScreen_clickOnFirstPostItem_showPostDetailsScreen() {
        val activityRule: ActivityScenario<PostContainerActivity> =
            ActivityScenario.launch(PostContainerActivity::class.java)

        // GIVEN post list screen is visible
        PostListVerifier {
            verifyPostListDisplayed()
        }
        Thread.sleep(POST_LIST_REQUEST_DELAY)

        // WHEN the first item of the post list is clicked
        PostListRobot {
            clickFirstPostItem()
        }

        // THEN the post details screen is opened.
        PostDetailsVerifier {
            verifyPostTitleIsDisplayed()
            verifyPostTitle(postTitle = MockingData.POST_TITLE)
            verifyPostDetailsIsDisplayed()
            verifyPostBody(postBody = MockingData.POST_BODY)
        }

        activityRule.close()
    }
}