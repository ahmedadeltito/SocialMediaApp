package com.ahmedadeltito.ui.home.model

/**
 * UI Model for comment item.
 */
data class CommentUiModel(
    val id: String,
    val postId: String,
    val name: String? = null,
    val email: String? = null,
    val body: String? = null
)