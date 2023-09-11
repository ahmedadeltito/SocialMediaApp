package com.ahmedadeltito.feature.post.apiservice

import com.ahmedadeltito.datasource.remote.models.PostResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * API Service interface that holds all post APIs.
 */
interface PostAPIService {

    @GET("posts")
    suspend fun getPosts(): List<PostResponse>?

    @GET("{postId}/posts")
    suspend fun getPost(@Path("postId") id: String): List<PostResponse>?

}