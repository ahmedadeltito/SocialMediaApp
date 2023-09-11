package com.ahmedadeltito.feature.post.usecase

import com.ahmedadeltito.feature.post.mapper.PostDataSourceToEntityMapper
import com.ahmedadeltito.feature.post.repository.PostRepository
import com.ahmedadeltito.feature.post.usecase.model.PostEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Use case class that get posts.
 */
class GetPostsUseCase
@Inject
constructor(
    private val repository: PostRepository,
    private val mapper: PostDataSourceToEntityMapper,
) {

    suspend fun invoke(): Flow<List<PostEntity>> = repository.getPosts().map { postListDataSource ->
        postListDataSource.map { postDataSource -> mapper.map(input = postDataSource) }
    }
}