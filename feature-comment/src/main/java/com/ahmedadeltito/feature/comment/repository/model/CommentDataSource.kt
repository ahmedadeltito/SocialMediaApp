package com.ahmedadeltito.feature.comment.repository.model

import com.ahmedadeltito.datasource.local.models.CommentLocal
import com.ahmedadeltito.datasource.remote.models.CommentResponse

/**
 * Data source model that is a result model from [CommentResponse], and [CommentLocal] models.
 */
data class CommentDataSource(
    val id: String,
    val postId: String,
    val name: String? = null,
    val email: String? = null,
    val body: String? = null
)