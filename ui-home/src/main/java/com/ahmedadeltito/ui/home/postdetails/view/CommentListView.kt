package com.ahmedadeltito.ui.home.postdetails.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ahmedadeltito.core.ui.Renderer
import com.ahmedadeltito.ui.home.databinding.ViewCommentListBinding
import com.ahmedadeltito.ui.home.postdetails.adapter.CommentListAdapter

/**
 * UI component designed to be the comment list.
 *
 * By default, the [CommentListView] is designed to
 * match the width of the parent view with a height wrapping its content.
 *
 * ```
 * <com.ahmedadeltito.ui.home.postdetails.view.CommentListView
 *     android:layout_width="match_parent"
 *     android:layout_height="wrap_content"/>
 * ```
 */
internal class CommentListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttrs, defStyleRes), Renderer<CommentListRendering> {

    private val viewBinding: ViewCommentListBinding = ViewCommentListBinding.inflate(
        LayoutInflater.from(context), this
    )

    private val adapter: CommentListAdapter = CommentListAdapter()

    private var rendering: CommentListRendering = CommentListRendering()

    init {
        viewBinding.commentListRv.adapter = adapter
        render { it }
    }

    override fun render(renderingUpdate: (CommentListRendering) -> CommentListRendering) {
        rendering = renderingUpdate(rendering)

        with(adapter) {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            submitList(rendering.state.commentList)
        }

        with(viewBinding) {
            noCommentListTv.visibility = if (rendering.state.commentList.isEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
            commentListCircularProgressIndicator.visibility = if (rendering.state.isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}