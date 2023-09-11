package com.ahmedadeltito.ui.home.postlist.view

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmedadeltito.core.ui.Renderer
import com.ahmedadeltito.ui.home.databinding.ViewPostListBinding
import com.ahmedadeltito.ui.home.postlist.adapter.PostListAdapter

/**
 * UI component designed to be the post list of the post list screen.
 *
 * By default, the [PostListView] is designed to
 * match the width of the parent view with a height wrapping its content.
 *
 * ```
 * <com.ahmedadeltito.ui.home.postlist.view.PostListView
 *     android:layout_width="match_parent"
 *     android:layout_height="wrap_content"/>
 * ```
 */
internal class PostListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0,
) : FrameLayout(context, attrs, defStyleAttrs, defStyleRes), Renderer<PostListRendering> {

    private val viewBinding: ViewPostListBinding = ViewPostListBinding.inflate(
        LayoutInflater.from(context), this
    )

    private val adapter: PostListAdapter = PostListAdapter()

    private var rendering: PostListRendering = PostListRendering()

    init {
        with(viewBinding.postListRv) {
            adapter = this@PostListView.adapter
            itemAnimator = null
        }
        render { it }
    }

    override fun render(renderingUpdate: (PostListRendering) -> PostListRendering) {
        rendering = renderingUpdate(rendering)

        with(adapter) {
            submitList(rendering.state.postList)
            onPostItemClicked = { postId ->
                rendering.onPostItemSelected(postId)
            }
        }

        with(viewBinding) {
            postListSwipeToRefresh.isRefreshing = rendering.state.isLoading
            postListSwipeToRefresh.setOnRefreshListener {
                rendering.onSwipeToRefresh.invoke()
            }
        }
    }
}
