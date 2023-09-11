package com.ahmedadeltito.ui.home.postlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmedadeltito.ui.home.R
import com.ahmedadeltito.ui.home.databinding.PostItemListBinding
import com.ahmedadeltito.ui.home.model.PostUiModel

class PostListAdapter :
    ListAdapter<PostUiModel, PostListAdapter.PostViewHolder>(PostItemDiffCallback()) {

    var onPostItemClicked: ((postId: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PostViewHolder =
        PostViewHolder(
            PostItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindTo(getItem(position), onPostItemClicked = onPostItemClicked)
    }

    override fun onCurrentListChanged(
        previousList: MutableList<PostUiModel>,
        currentList: MutableList<PostUiModel>
    ) {
        super.onCurrentListChanged(previousList, currentList)
        notifyDataSetChanged()
    }

    inner class PostViewHolder(private val binding: PostItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(post: PostUiModel, onPostItemClicked: ((postId: String) -> Unit)?) {
            with(binding) {
                postTitleTv.text = itemView.context.getString(R.string.post_title, post.title)
                postContentTv.text = itemView.context.getString(R.string.post_body, post.body)
                root.setOnClickListener {
                    onPostItemClicked?.invoke(post.id)
                }
            }
        }
    }
}