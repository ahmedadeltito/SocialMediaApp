package com.ahmedadeltito.ui.home.page

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers
import com.ahmedadeltito.ui.home.postdetails.fragment.PostDetailsFragment
import org.hamcrest.Matcher

/**
 * A Page for the [PostDetailsFragment] view.
 */
object PostDetailsPage {

    /**
     * Matcher for the view with the [PostDetailsFragment]'s post title text field.
     */
    val postDetailsTitleTv: Matcher<View>
        get() = ViewMatchers.withId(com.ahmedadeltito.ui.home.R.id.post_details_title_tv)

    /**
     * Matcher for the view with the [PostDetailsFragment]'s post details price text field.
     */
    val postDetailsBodyTv: Matcher<View>
        get() = ViewMatchers.withId(com.ahmedadeltito.ui.home.R.id.post_details_body_tv)
}