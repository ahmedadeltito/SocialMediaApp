package com.ahmedadeltito.ui.home.postdetails.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ahmedadeltito.ui.home.model.CommentUiModel

class CommentItemDiffCallback : DiffUtil.ItemCallback<CommentUiModel>() {
    override fun areItemsTheSame(oldItem: CommentUiModel, newItem: CommentUiModel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: CommentUiModel, newItem: CommentUiModel): Boolean =
        oldItem == newItem
}