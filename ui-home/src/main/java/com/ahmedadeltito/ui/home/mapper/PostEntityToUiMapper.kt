package com.ahmedadeltito.ui.home.mapper

import com.ahmedadeltito.core.mapper.Mapper
import com.ahmedadeltito.feature.post.usecase.model.PostEntity
import com.ahmedadeltito.ui.home.model.PostUiModel

/**
 * Mapper to map [PostEntity] to [PostUiModel]
 */
class PostEntityToUiMapper : Mapper<PostEntity, PostUiModel> {
    override fun map(input: PostEntity): PostUiModel = with(input) {
        PostUiModel(id = id, userId = userId, title = title, body = body)
    }
}