package com.ahmedadeltito.ui.home.matchers

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.Matcher

object CustomMatchers {

    /**
     * Helper function that matches the logic that we want on a specific index of the view in a recycler view for an
     * example and we want to click on specific index or item inside this recycler view.
     *
     * @param viewId is an optional parameter in case of we want to send a specific view id that fires the click
     * lister action. Null by default.
     *
     * @return the specific matcher that we want to check and fire the click action on it.
     */
    fun clickView(viewId: Int? = null): ViewAction = object : ViewAction {
        override fun getConstraints(): Matcher<View>? = null

        override fun getDescription(): String = "Click on a child view."

        override fun perform(uiController: UiController?, view: View) {
            viewId?.let { id ->
                view.findViewById<View>(id).performClick()
            } ?: run {
                view.performClick()
            }
        }
    }
}