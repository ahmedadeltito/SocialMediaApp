package com.ahmedadeltito.feature.comment.repository

import com.ahmedadeltito.core.extensions.isEqual
import com.ahmedadeltito.datasource.local.dao.CommentDao
import com.ahmedadeltito.datasource.local.models.CommentLocal
import com.ahmedadeltito.datasource.remote.models.CommentResponse
import com.ahmedadeltito.feature.comment.apiservice.CommentAPIService
import com.ahmedadeltito.feature.comment.mapper.CommentLocalToDataSourceMapper
import com.ahmedadeltito.feature.comment.mapper.CommentResponseToDataSourceMapper
import com.ahmedadeltito.feature.comment.mapper.CommentResponseToLocalMapper
import com.ahmedadeltito.feature.comment.repository.model.CommentDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CommentRepository
@Inject
constructor(
    private val local: CommentDao,
    private val remote: CommentAPIService,
    private val commentResponseToDataSourceMapper: CommentResponseToDataSourceMapper,
    private val commentResponseToLocalMapper: CommentResponseToLocalMapper,
    private val commentLocalToDataSourceMapper: CommentLocalToDataSourceMapper,
) {

    internal suspend fun getComments(
        postId: String
    ): Flow<List<CommentDataSource>> = flow {
        val localComments = getLocalComments(postId = postId)
        if (localComments?.isNotEmpty() == true) {
            emit(
                value = localComments.map { localComment ->
                    commentLocalToDataSourceMapper.map(input = localComment)
                }
            )
        }
        getRemoteComments(postId = postId)?.let { remoteComments ->
            if (localComments?.isEqual(second = remoteComments) == false) {
                remoteComments.map { remoteComment ->
                    local.insertComment(commentResponseToLocalMapper.map(input = remoteComment))
                }
                emit(
                    value = remoteComments.map { remoteComment ->
                        commentResponseToDataSourceMapper.map(input = remoteComment)
                    }
                )
            }
        }
    }

    private suspend fun getLocalComments(
        postId: String
    ): List<CommentLocal>? = local.getComments(postId = postId)

    private suspend fun getRemoteComments(postId: String): List<CommentResponse>? =
        remote.getComments(postId = postId)

}