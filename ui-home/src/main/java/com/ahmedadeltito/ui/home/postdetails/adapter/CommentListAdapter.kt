package com.ahmedadeltito.ui.home.postdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmedadeltito.ui.home.R
import com.ahmedadeltito.ui.home.databinding.CommentItemListBinding
import com.ahmedadeltito.ui.home.model.CommentUiModel

class CommentListAdapter :
    ListAdapter<CommentUiModel, CommentListAdapter.CommentViewHolder>(CommentItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CommentViewHolder =
        CommentViewHolder(
            CommentItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class CommentViewHolder(private val binding: CommentItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(comment: CommentUiModel) {
            with(binding) {
                commentTitleTv.text =
                    itemView.context.getString(R.string.comment_name, comment.name)
                commentEmailTv.text =
                    itemView.context.getString(R.string.comment_email, comment.email)
                commentBodyTv.text =
                    itemView.context.getString(R.string.comment_body, comment.body)
            }
        }
    }
}