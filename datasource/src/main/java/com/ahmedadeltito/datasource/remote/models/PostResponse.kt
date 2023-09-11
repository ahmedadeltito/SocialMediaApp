package com.ahmedadeltito.datasource.remote.models

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Response model of posts.
 */
@Keep
@JsonClass(generateAdapter = true)
data class PostResponse(

    @Json(name = "id")
    val id: String,

    @Json(name = "user_id")
    val userId: String,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "body")
    val body: String? = null

)