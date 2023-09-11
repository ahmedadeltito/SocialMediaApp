package com.ahmedadeltito.ui.home.model

/**
 * UI Model for post item.
 */
data class PostUiModel(
    val id: String,
    val userId: String,
    val title: String? = null,
    val body: String? = null
)