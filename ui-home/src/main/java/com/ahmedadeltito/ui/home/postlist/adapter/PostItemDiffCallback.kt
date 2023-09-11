package com.ahmedadeltito.ui.home.postlist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ahmedadeltito.ui.home.model.PostUiModel

class PostItemDiffCallback : DiffUtil.ItemCallback<PostUiModel>() {
    override fun areItemsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean =
        oldItem == newItem
}