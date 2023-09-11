package com.ahmedadeltito.ui.home.postdetails.view

/**
 * Everything that the Comment List needs to render and dispatch events.
 *
 * This class is designed to be created with the associated [Builder] as follows:
 * ```
 *      val rendering = CommentListRendering.Builder()
 *          .state { state.copy(commentList = myCommentList) }
 *          .build()
 * ```
 */
internal class CommentListRendering internal constructor(
    builder: Builder,
) {
    internal val onReloadCommentList: OnReloadCommentList = builder.onReloadCommentList
    internal val state: CommentListState = builder.state

    constructor() : this(Builder())

    /**
     * Creates a new [Builder] based on this [CommentListRendering].
     */
    fun toBuilder(): Builder = Builder(this)

    /**
     * Creates a new instances of the [Builder].
     */
    class Builder() {
        internal var onReloadCommentList: OnReloadCommentList = { }
        internal var state: CommentListState = CommentListState()

        /**
         *  Configures this [Builder] based on the given [rendering].
         */
        internal constructor(rendering: CommentListRendering = CommentListRendering()) : this() {
            this.onReloadCommentList = rendering.onReloadCommentList
            this.state = rendering.state
        }

        /**
         * Sets the listener to be called when the on reload comment list is clicked.
         *
         * Sample usage:
         * ```
         * val rendering = CommentListRendering.Builder()
         *      .onReloadCommentList { }
         *      .build()
         * ```
         *
         * @param onReloadCommentList the listener to be called
         *
         * @return this [Builder]
         */
        fun onReloadCommentList(onReloadCommentList: OnReloadCommentList): Builder = apply {
            this.onReloadCommentList = onReloadCommentList
        }

        /**
         * Applies the [stateUpdate] on the current [CommentListState] to produce a new state to render.
         *
         * Sample Usage:
         * ```
         * val rendering = CommentListRendering.Builder()
         *      .state { state.copy(commentList = myCommentList) }
         *      .build()
         * ```
         */
        fun state(stateUpdate: (CommentListState) -> CommentListState): Builder = apply {
            this.state = stateUpdate(state)
        }

        /**
         * Builds the [CommentListRendering].
         */
        fun build(): CommentListRendering = CommentListRendering(this)
    }
}

/**
 * A listener called when the [CommentListState] is on reload comment list clicked.
 */
internal typealias OnReloadCommentList = () -> Unit