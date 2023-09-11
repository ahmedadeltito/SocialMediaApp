package com.ahmedadeltito.feature.comment.apiservice

import com.ahmedadeltito.datasource.remote.models.CommentResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * API Service interface that holds all post APIs.
 */
interface CommentAPIService {

    @GET("posts/{postId}/comments")
    suspend fun getComments(@Path("postId") postId: String): List<CommentResponse>?

}