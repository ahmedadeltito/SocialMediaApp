package com.ahmedadeltito.feature.post.mapper

import com.ahmedadeltito.core.mapper.Mapper
import com.ahmedadeltito.datasource.local.models.PostLocal
import com.ahmedadeltito.datasource.remote.models.PostResponse
import com.ahmedadeltito.feature.post.repository.model.PostDataSource

/**
 * Mapper to map [PostResponse] to [PostDataSource]
 */
class PostResponseToDataSourceMapper : Mapper<PostResponse, PostDataSource> {
    override fun map(input: PostResponse): PostDataSource = with(input) {
        PostDataSource(id = id, userId = userId, title = title, body = body)
    }
}

/**
 * Mapper to map [PostLocal] to [PostDataSource]
 */
class PostLocalToDataSourceMapper : Mapper<PostLocal, PostDataSource> {
    override fun map(input: PostLocal): PostDataSource = with(input) {
        PostDataSource(id = id, userId = userId, title = title, body = body)
    }
}

/**
 * Mapper to map [PostResponse] to [PostLocal]
 */
class PostResponseToLocalMapper : Mapper<PostResponse, PostLocal> {
    override fun map(input: PostResponse): PostLocal = with(input) {
        PostLocal(id = id, userId = userId, title = title, body = body)
    }
}