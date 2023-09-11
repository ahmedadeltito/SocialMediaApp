package com.ahmedadeltito.feature.comment.usecase

import com.ahmedadeltito.feature.comment.mapper.CommentDataSourceToEntityMapper
import com.ahmedadeltito.feature.comment.repository.CommentRepository
import com.ahmedadeltito.feature.comment.usecase.model.CommentEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Use case class that get comments.
 */
class GetCommentsUseCase
@Inject
constructor(
    private val repository: CommentRepository,
    private val mapper: CommentDataSourceToEntityMapper,
) {

    suspend fun invoke(
        postId: String
    ): Flow<List<CommentEntity>> = repository.getComments(
        postId = postId
    ).map { commentListDataSource ->
        commentListDataSource.map { commentDataSource -> mapper.map(input = commentDataSource) }
    }
}