package com.ahmedadeltito.ui.home.postlist.view

import com.ahmedadeltito.ui.home.postlist.view.PostListRendering.Builder

/**
 * Everything that the Post List needs to render and dispatch events.
 *
 * This class is designed to be created with the associated [Builder] as follows:
 * ```
 *      val rendering = PostListRendering.Builder()
 *          .state { state.copy(postList = myPostList) }
 *          .build()
 * ```
 */
internal class PostListRendering internal constructor(
    builder: Builder,
) {
    internal val onPostItemSelected: OnPostItemSelected = builder.onPostItemSelected
    internal val onSwipeToRefresh: OnSwipeToRefresh = builder.onSwipeToRefresh
    internal val state: PostListState = builder.state

    constructor() : this(Builder())

    /**
     * Creates a new [Builder] based on this [PostListRendering].
     */
    fun toBuilder(): Builder = Builder(this)

    /**
     * Creates a new instances of the [Builder].
     */
    class Builder() {
        internal var onPostItemSelected: OnPostItemSelected = { _ -> }
        internal var onSwipeToRefresh: OnSwipeToRefresh = { }
        internal var state: PostListState = PostListState()

        /**
         *  Configures this [Builder] based on the given [rendering].
         */
        internal constructor(rendering: PostListRendering = PostListRendering()) : this() {
            this.onPostItemSelected = rendering.onPostItemSelected
            this.onSwipeToRefresh = rendering.onSwipeToRefresh
            this.state = rendering.state
        }

        /**
         * Sets the listener to be called when the ID of the post item is selected.
         *
         * Sample usage:
         * ```
         * val rendering = PostListRendering.Builder()
         *      .onPostItemSelected { postId -> }
         *      .build()
         * ```
         *
         * @param onPostItemSelected the listener to be called
         *
         * @return this [Builder]
         */
        fun onPostItemSelected(onPostItemSelected: OnPostItemSelected): Builder = apply {
            this.onPostItemSelected = onPostItemSelected
        }

        /**
         * Sets the listener to be called when the swipe to refresh is triggered.
         *
         * Sample usage:
         * ```
         * val rendering = PostListRendering.Builder()
         *      .onSwipeToRefresh { }
         *      .build()
         * ```
         *
         * @param onSwipeToRefresh the listener to be called
         *
         * @return this [Builder]
         */
        fun onSwipeToRefresh(onSwipeToRefresh: OnSwipeToRefresh): Builder = apply {
            this.onSwipeToRefresh = onSwipeToRefresh
        }

        /**
         * Applies the [stateUpdate] on the current [PostListState] to produce a new state to render.
         *
         * Sample Usage:
         * ```
         * val rendering = PostListRendering.Builder()
         *      .state { state.copy(postList = myPostList) }
         *      .build()
         * ```
         */
        fun state(stateUpdate: (PostListState) -> PostListState): Builder = apply {
            this.state = stateUpdate(state)
        }

        /**
         * Builds the [PostListRendering].
         */
        fun build(): PostListRendering = PostListRendering(this)
    }
}

/**
 * A listener called when the ID of the post item is selected
 */
internal typealias OnPostItemSelected = (String) -> Unit

/**
 * A listener called when the [PostListView] is swiped to refresh.
 */
internal typealias OnSwipeToRefresh = () -> Unit