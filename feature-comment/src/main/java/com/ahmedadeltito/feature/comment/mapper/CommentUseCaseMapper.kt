package com.ahmedadeltito.feature.comment.mapper

import com.ahmedadeltito.core.mapper.Mapper
import com.ahmedadeltito.feature.comment.repository.model.CommentDataSource
import com.ahmedadeltito.feature.comment.usecase.model.CommentEntity

/**
 * Mapper to map [CommentDataSource] to [CommentEntity]
 */
class CommentDataSourceToEntityMapper : Mapper<CommentDataSource, CommentEntity> {
    override fun map(input: CommentDataSource): CommentEntity = with(input) {
        CommentEntity(id = id, postId = postId, name = name, email = email, body = body)
    }
}