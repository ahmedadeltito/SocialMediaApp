package com.ahmedadeltito.feature.post.repository.model

import com.ahmedadeltito.datasource.local.models.PostLocal
import com.ahmedadeltito.datasource.remote.models.PostResponse

/**
 * Data source model that is a result model from [PostResponse], and [PostLocal] models.
 */
data class PostDataSource(
    val id: String,
    val userId: String,
    val title: String? = null,
    val body: String? = null
)