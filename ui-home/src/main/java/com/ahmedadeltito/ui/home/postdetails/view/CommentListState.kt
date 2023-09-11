package com.ahmedadeltito.ui.home.postdetails.view

import com.ahmedadeltito.ui.home.model.CommentUiModel

/**
 * Holds the state needed by the Comment List to render.
 *
 * This class is designed to be created with the associated [Builder] as follows:
 * ```
 *      val state = CommentListState.Builder().build()
 * ```
 * Alternatively, you can copy an existing [CommentListState] altering some of its properties:
 * ```
 *      val commentList = state.copy(commentList = List<CommentUiModel>())
 * ```
 *
 * @param commentList represents the list of the comment.
 * @param isLoading indicates if the list is loading or not.
 */
internal data class CommentListState internal constructor(
    internal val commentList: List<CommentUiModel> = emptyList(),
    internal val isLoading: Boolean = true,
) {
    /**
     * Creates a new [Builder] based on this [CommentListState].
     */
    fun toBuilder(): Builder = Builder(this)

    class Builder constructor() {
        private var state = CommentListState()

        /**
         * Creates a new [Builder] based on an existing [CommentListState].
         */
        internal constructor(state: CommentListState) : this() {
            this.state = state
        }

        /**
         * Sets List of [CommentUiModel] that represents list of comments
         *
         * @param commentList Holds the comment list
         */
        fun commentList(commentList: List<CommentUiModel>): Builder = apply {
            state = state.copy(commentList = commentList)
        }

        /**
         * Sets the comment list to be is loading or not.
         *
         * @param isLoading the boolean state of the comment list.
         */
        fun isLoading(isLoading: Boolean): Builder = apply {
            state = state.copy(isLoading = isLoading)
        }

        /**
         * Builds the [CommentListState]
         */
        fun build(): CommentListState = state
    }
}