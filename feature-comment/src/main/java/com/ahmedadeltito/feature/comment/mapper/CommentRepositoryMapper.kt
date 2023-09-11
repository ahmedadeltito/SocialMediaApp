package com.ahmedadeltito.feature.comment.mapper

import com.ahmedadeltito.core.mapper.Mapper
import com.ahmedadeltito.datasource.local.models.CommentLocal
import com.ahmedadeltito.datasource.remote.models.CommentResponse
import com.ahmedadeltito.feature.comment.repository.model.CommentDataSource

/**
 * Mapper to map [CommentResponse] to [CommentDataSource]
 */
class CommentResponseToDataSourceMapper : Mapper<CommentResponse, CommentDataSource> {
    override fun map(input: CommentResponse): CommentDataSource = with(input) {
        CommentDataSource(id = id, postId = postId, name = name, email = email, body = body)
    }
}

/**
 * Mapper to map [CommentLocal] to [CommentDataSource]
 */
class CommentLocalToDataSourceMapper : Mapper<CommentLocal, CommentDataSource> {
    override fun map(input: CommentLocal): CommentDataSource = with(input) {
        CommentDataSource(id = id, postId = postId, name = name, email = email, body = body)
    }
}

/**
 * Mapper to map [CommentResponse] to [CommentLocal]
 */
class CommentResponseToLocalMapper : Mapper<CommentResponse, CommentLocal> {
    override fun map(input: CommentResponse): CommentLocal = with(input) {
        CommentLocal(id = id, postId = postId, name = name, email = email, body = body)
    }
}