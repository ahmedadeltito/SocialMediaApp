package com.ahmedadeltito.datasource.remote.models

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Response model of comments.
 */
@Keep
@JsonClass(generateAdapter = true)
data class CommentResponse(

    @Json(name = "id")
    val id: String,

    @Json(name = "post_id")
    val postId: String,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "email")
    val email: String? = null,

    @Json(name = "body")
    val body: String? = null

)