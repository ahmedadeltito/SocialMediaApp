package com.ahmedadeltito.ui.home.mapper

import com.ahmedadeltito.core.mapper.Mapper
import com.ahmedadeltito.feature.comment.usecase.model.CommentEntity
import com.ahmedadeltito.ui.home.model.CommentUiModel

/**
 * Mapper to map [CommentEntity] to [CommentUiModel]
 */
class CommentEntityToUiMapper : Mapper<CommentEntity, CommentUiModel> {
    override fun map(input: CommentEntity): CommentUiModel = with(input) {
        CommentUiModel(id = id, postId = postId, name = name, email = email, body = body)
    }
}