package com.ahmedadeltito.ui.home.postdetails.view

import com.ahmedadeltito.ui.home.model.PostUiModel

/**
 * Holds the state needed by the Post List to render.
 *
 * This class is designed to be created with the associated [Builder] as follows:
 * ```
 *      val state = PostDetailsState.Builder().build()
 * ```
 * Alternatively, you can copy an existing [PostDetailsState] altering some of its properties:
 * ```
 *      val postDetails = state.copy(postTitle = "Post Title")
 * ```
 *
 * @param postTitle represents the title of the post.
 * @param postBody represents the body of the post
 * @param isLoading indicates if the view is loading or not.
 */
internal data class PostDetailsState internal constructor(
    internal val postTitle: String = "",
    internal val postBody: String = "",
    internal val isLoading: Boolean = true,
) {
    /**
     * Creates a new [Builder] based on this [PostDetailsState].
     */
    fun toBuilder(): Builder = Builder(this)

    class Builder constructor() {
        private var state = PostDetailsState()

        /**
         * Creates a new [Builder] based on an existing [PostDetailsState].
         */
        internal constructor(state: PostDetailsState) : this() {
            this.state = state
        }

        /**
         * Sets the title of the [PostUiModel]
         *
         * @param postTitle Holds the post title
         */
        fun postTitle(postTitle: String): Builder = apply {
            state = state.copy(postTitle = postTitle)
        }

        /**
         * Sets the body of the [PostUiModel]
         *
         * @param postBody Holds the post title
         */
        fun postBody(postBody: String): Builder = apply {
            state = state.copy(postBody = postBody)
        }

        /**
         * Sets the post details screen to be is loading or not.
         *
         * @param isLoading the boolean state of the post details screen that should swipe to refresh.
         */
        fun isLoading(isLoading: Boolean): Builder = apply {
            state = state.copy(isLoading = isLoading)
        }

        /**
         * Builds the [PostDetailsState]
         */
        fun build(): PostDetailsState = state
    }
}