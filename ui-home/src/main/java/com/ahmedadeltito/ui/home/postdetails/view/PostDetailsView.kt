package com.ahmedadeltito.ui.home.postdetails.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ahmedadeltito.core.ui.Renderer
import com.ahmedadeltito.ui.home.databinding.ViewPostDetailsBinding

/**
 * UI component designed to be the post details.
 *
 * By default, the [PostDetailsView] is designed to
 * match the width of the parent view with a height wrapping its content.
 *
 * ```
 * <com.ahmedadeltito.ui.home.postdetails.view.PostDetailsView
 *     android:layout_width="match_parent"
 *     android:layout_height="wrap_content"/>
 * ```
 */
internal class PostDetailsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttrs, defStyleRes), Renderer<PostDetailsRendering> {

    private val viewBinding: ViewPostDetailsBinding = ViewPostDetailsBinding.inflate(
        LayoutInflater.from(context), this
    )

    private var rendering: PostDetailsRendering = PostDetailsRendering()

    init {
        render { it }
    }

    override fun render(renderingUpdate: (PostDetailsRendering) -> PostDetailsRendering) {
        rendering = renderingUpdate(rendering)

        with(viewBinding) {
            postDetailsTitleTv.text = rendering.state.postTitle
            postDetailsBodyTv.text = rendering.state.postBody
            postDetailsCircularProgressIndicator.visibility = if (rendering.state.isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}