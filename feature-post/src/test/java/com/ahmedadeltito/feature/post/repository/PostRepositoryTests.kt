package com.ahmedadeltito.feature.post.repository

import com.ahmedadeltito.datasource.local.dao.PostDao
import com.ahmedadeltito.feature.post.MockingData
import com.ahmedadeltito.feature.post.apiservice.PostAPIService
import com.ahmedadeltito.feature.post.mapper.PostLocalToDataSourceMapper
import com.ahmedadeltito.feature.post.mapper.PostResponseToDataSourceMapper
import com.ahmedadeltito.feature.post.mapper.PostResponseToLocalMapper
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for [PostRepository]
 */
internal class PostRepositoryTests {

    private lateinit var postRepository: PostRepository

    private val postResponseToDataSourceMapper = PostResponseToDataSourceMapper()
    private val postResponseToLocalMapper = PostResponseToLocalMapper()
    private val postLocalToDataSourceMapper = PostLocalToDataSourceMapper()

    private val local = mockk<PostDao>(relaxed = true)
    private val remote = mockk<PostAPIService>(relaxed = true)

    @Before
    fun setup() {
        postRepository = PostRepository(
            local = local,
            remote = remote,
            postResponseToDataSourceMapper = postResponseToDataSourceMapper,
            postResponseToLocalMapper = postResponseToLocalMapper,
            postLocalToDataSourceMapper = postLocalToDataSourceMapper
        )
    }

    @Test
    fun `getPosts emits local post list first then remote post list`() {
        /**
         * GIVEN
         * - [local] is returning [MockingData.postLocalList]
         * - [remote] is returning [MockingData.postRemoteList]
         */
        coEvery { local.getPosts() } returns MockingData.postLocalList
        coEvery { remote.getPosts() } returns MockingData.postRemoteList

        val postLocalDataSourceList = MockingData.postLocalList.map { postLocal ->
            postLocalToDataSourceMapper.map(input = postLocal)
        }
        val postRemoteDataSourceList = MockingData.postRemoteList.map { postResponse ->
            postResponseToDataSourceMapper.map(input = postResponse)
        }

        runBlocking {
            /**
             * WHEN [postRepository] is calling get posts.
             */
            val getPosts = postRepository.getPosts()

            /**
             * THEN the local post list is emitted first.
             * AND the remote post list is emitted second.
             */
            assertThat(getPosts.first()).isEqualTo(postLocalDataSourceList)
            assertThat(getPosts.last()).isEqualTo(postRemoteDataSourceList)
        }
    }

    @Test
    fun `getPosts emits remote post list only in case of empty local post list`() {
        /**
         * GIVEN
         * - [local] is returning [emptyList]
         * - [remote] is returning [MockingData.postRemoteList]
         */
        coEvery { local.getPosts() } returns emptyList()
        coEvery { remote.getPosts() } returns MockingData.postRemoteList

        val postRemoteDataSourceList = MockingData.postRemoteList.map { postRemote ->
            postResponseToDataSourceMapper.map(input = postRemote)
        }

        runBlocking {
            /**
             * WHEN [postRepository] is calling get posts.
             */
            val getPosts = postRepository.getPosts()

            /**
             * THEN the local post list is emitted only.
             */
            assertThat(getPosts.first()).isEqualTo(postRemoteDataSourceList)
        }
    }

    @Test
    fun `getPost emits local post item first then remote post item`() {
        /**
         * GIVEN
         * - [local] is returning [MockingData.postLocal]
         * - [remote] is returning [MockingData.postRemote]
         */
        coEvery { local.getPost(id = "115") } returns MockingData.postLocal
        coEvery { remote.getPost(id = "115") } returns listOf(MockingData.postRemote)

        val postLocal = postLocalToDataSourceMapper.map(input = MockingData.postLocal)
        val postRemote = postResponseToDataSourceMapper.map(input = MockingData.postRemote)

        runBlocking {
            /**
             * WHEN [postRepository] is calling get a post.
             */
            val getPost = postRepository.getPost(id = "115")

            /**
             * THEN the local post item is emitted first,
             * AND the remote post item is emitted second.
             */
            assertThat(getPost.first()).isEqualTo(postLocal)
            assertThat(getPost.last()).isEqualTo(postRemote)
        }
    }

    @Test
    fun `getPost emits remote post item only in case of empty local post item`() {
        /**
         * GIVEN
         * - [local] is returning null
         * - [remote] is returning [MockingData.postRemote]
         */
        coEvery { local.getPost(id = "115") } returns null
        coEvery { remote.getPost(id = "115") } returns listOf(MockingData.postRemote)

        val postRemote = postResponseToDataSourceMapper.map(input = MockingData.postRemote)

        runBlocking {
            /**
             * WHEN [postRepository] is calling get a post.
             */
            val getPost = postRepository.getPost(id = "115")

            /**
             * THEN the local post item is emitted first,
             * AND the remote post item is emitted second.
             */
            assertThat(getPost.first()).isEqualTo(postRemote)
        }
    }
}