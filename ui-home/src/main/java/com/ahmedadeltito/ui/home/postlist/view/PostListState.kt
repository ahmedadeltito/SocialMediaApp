package com.ahmedadeltito.ui.home.postlist.view

import com.ahmedadeltito.ui.home.model.PostUiModel
import com.ahmedadeltito.ui.home.postlist.view.PostListState.Builder

/**
 * Holds the state needed by the Post List to render.
 *
 * This class is designed to be created with the associated [Builder] as follows:
 * ```
 *      val state = PostListState.Builder().build()
 * ```
 * Alternatively, you can copy an existing [PostListState] altering some of its properties:
 * ```
 *      val postList = state.copy(postList = List<PostUiModel>())
 * ```
 *
 * @param postList represents the list of the post.
 * @param isLoading indicates if the list is loading or not.
 */
internal data class PostListState internal constructor(
    internal val postList: List<PostUiModel> = emptyList(),
    internal val isLoading: Boolean = true,
) {
    /**
     * Creates a new [Builder] based on this [PostListState].
     */
    fun toBuilder(): Builder = Builder(this)

    class Builder constructor() {
        private var state = PostListState()

        /**
         * Creates a new [Builder] based on an existing [PostListState].
         */
        internal constructor(state: PostListState) : this() {
            this.state = state
        }

        /**
         * Sets List of [PostUiModel] that represents list of posts
         *
         * @param postList Holds the post list
         */
        fun postList(postList: List<PostUiModel>): Builder = apply {
            state = state.copy(postList = postList)
        }

        /**
         * Sets the post list to be is loading or not.
         *
         * @param isLoading the boolean state of the post list that should swipe to refresh.
         */
        fun isLoading(isLoading: Boolean): Builder = apply {
            state = state.copy(isLoading = isLoading)
        }

        /**
         * Builds the [PostListState]
         */
        fun build(): PostListState = state
    }
}