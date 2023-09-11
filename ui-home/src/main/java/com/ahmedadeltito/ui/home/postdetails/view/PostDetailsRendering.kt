package com.ahmedadeltito.ui.home.postdetails.view

/**
 * Everything that the Post Details needs to render and dispatch events.
 *
 * This class is designed to be created with the associated [Builder] as follows:
 * ```
 *      val rendering = PostDetailsRendering.Builder()
 *          .state { state.copy(onSwipeToRefresh = {}) }
 *          .build()
 * ```
 */
internal class PostDetailsRendering internal constructor(
    builder: Builder,
) {
    internal val onSwipeToRefresh: OnSwipeToRefresh = builder.onSwipeToRefresh
    internal val state: PostDetailsState = builder.state

    constructor() : this(Builder())

    /**
     * Creates a new [Builder] based on this [PostDetailsRendering].
     */
    fun toBuilder(): Builder = Builder(this)

    /**
     * Creates a new instances of the [Builder].
     */
    class Builder() {
        internal var onSwipeToRefresh: OnSwipeToRefresh = { }
        internal var state: PostDetailsState = PostDetailsState()

        /**
         *  Configures this [Builder] based on the given [rendering].
         */
        internal constructor(rendering: PostDetailsRendering = PostDetailsRendering()) : this() {
            this.onSwipeToRefresh = rendering.onSwipeToRefresh
            this.state = rendering.state
        }

        /**
         * Sets the listener to be called when the swipe to refresh is triggered.
         *
         * Sample usage:
         * ```
         * val rendering = PostDetailsRendering.Builder()
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
         * Applies the [stateUpdate] on the current [PostDetailsState] to produce a new state to render.
         *
         * Sample Usage:
         * ```
         * val rendering = PostDetailsRendering.Builder()
         *      .state { state.copy(onSwipeToRefresh = {}) }
         *      .build()
         * ```
         */
        fun state(stateUpdate: (PostDetailsState) -> PostDetailsState): Builder = apply {
            this.state = stateUpdate(state)
        }

        /**
         * Builds the [PostDetailsRendering].
         */
        fun build(): PostDetailsRendering = PostDetailsRendering(this)
    }
}

/**
 * A listener called when the [PostDetailsView] is swiped to refresh.
 */
internal typealias OnSwipeToRefresh = () -> Unit