package com.ahmedadeltito.feature.post.usecase.model

import com.ahmedadeltito.feature.post.repository.model.PostDataSource

/**
 * Entity model that is a result of [PostDataSource].
 */
data class PostEntity(
    val id: String,
    val userId: String,
    val title: String? = null,
    val body: String? = null
)