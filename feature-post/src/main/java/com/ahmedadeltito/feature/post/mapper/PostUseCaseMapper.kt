package com.ahmedadeltito.feature.post.mapper

import com.ahmedadeltito.core.mapper.Mapper
import com.ahmedadeltito.feature.post.repository.model.PostDataSource
import com.ahmedadeltito.feature.post.usecase.model.PostEntity

/**
 * Mapper to map [PostDataSource] to [PostEntity]
 */
class PostDataSourceToEntityMapper : Mapper<PostDataSource, PostEntity> {
    override fun map(input: PostDataSource): PostEntity = with(input) {
        PostEntity(id = id, userId = userId, title = title, body = body)
    }
}