package com.ahmedadeltito.feature.comment.usecase.model

import com.ahmedadeltito.feature.comment.repository.model.CommentDataSource

/**
 * Entity model that is a result of [CommentDataSource].
 */
data class CommentEntity(
    val id: String,
    val postId: String,
    val name: String? = null,
    val email: String? = null,
    val body: String? = null
)