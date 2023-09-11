package com.ahmedadeltito.feature.post.repository

import com.ahmedadeltito.core.extensions.isEqual
import com.ahmedadeltito.datasource.local.dao.PostDao
import com.ahmedadeltito.datasource.local.models.PostLocal
import com.ahmedadeltito.datasource.remote.models.PostResponse
import com.ahmedadeltito.feature.post.apiservice.PostAPIService
import com.ahmedadeltito.feature.post.mapper.PostLocalToDataSourceMapper
import com.ahmedadeltito.feature.post.mapper.PostResponseToDataSourceMapper
import com.ahmedadeltito.feature.post.mapper.PostResponseToLocalMapper
import com.ahmedadeltito.feature.post.repository.model.PostDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository class that holds all the data source posts transactions.
 */
class PostRepository
@Inject
constructor(
    private val local: PostDao,
    private val remote: PostAPIService,
    private val postResponseToDataSourceMapper: PostResponseToDataSourceMapper,
    private val postResponseToLocalMapper: PostResponseToLocalMapper,
    private val postLocalToDataSourceMapper: PostLocalToDataSourceMapper,
) {

    internal suspend fun getPosts(): Flow<List<PostDataSource>> = flow {
        val localPosts = getLocalPosts()
        if (localPosts?.isNotEmpty() == true) {
            emit(
                value = localPosts.map { localPost ->
                    postLocalToDataSourceMapper.map(input = localPost)
                }
            )
        }
        getRemotePosts()?.let { remotePosts ->
            if (localPosts?.isEqual(second = remotePosts) == false) {
                remotePosts.map { remotePost ->
                    local.insertPost(postResponseToLocalMapper.map(input = remotePost))
                }
                emit(
                    value = remotePosts.map { remotePost ->
                        postResponseToDataSourceMapper.map(input = remotePost)
                    }
                )
            }
        }
    }

    internal suspend fun getPost(id: String): Flow<PostDataSource> = flow {
        val localPost = getLocalPost(id = id)
        localPost?.let {
            emit(value = postLocalToDataSourceMapper.map(input = it))
        }
        val remotePost = getRemotePost(id = id)
        if (remotePost != null) {
            val localDataSource = if (localPost != null) {
                postLocalToDataSourceMapper.map(input = localPost)
            } else {
                null
            }
            val remoteDataSource = postResponseToDataSourceMapper.map(input = remotePost)
            if (localDataSource == null || localDataSource != remoteDataSource) {
                local.insertPost(postResponseToLocalMapper.map(input = remotePost))
                emit(value = remoteDataSource)
            }
        }
    }

    private suspend fun getLocalPosts(): List<PostLocal>? = local.getPosts()

    private suspend fun getLocalPost(id: String): PostLocal? = local.getPost(id = id)

    private suspend fun getRemotePosts(): List<PostResponse>? = remote.getPosts()

    private suspend fun getRemotePost(id: String): PostResponse? = remote.getPost(id = id)?.first()
}