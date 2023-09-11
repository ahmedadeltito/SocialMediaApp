package com.ahmedadeltito.feature.post.usecase

import com.ahmedadeltito.feature.post.MockingData
import com.ahmedadeltito.feature.post.mapper.PostDataSourceToEntityMapper
import com.ahmedadeltito.feature.post.repository.PostRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for [GetPostsUseCase]
 */
internal class GetPostsUseCaseTests {

    private lateinit var getPostsUseCase: GetPostsUseCase

    private val postDataSourceToEntityMapper = PostDataSourceToEntityMapper()

    private val postRepository = mockk<PostRepository>(relaxed = true)

    @Before
    fun setup() {
        getPostsUseCase = GetPostsUseCase(
            repository = postRepository,
            mapper = postDataSourceToEntityMapper
        )
    }

    @Test
    fun `getPosts emits local post list`() {
        /**
         * GIVEN [postRepository] is returning [MockingData.postsLocalDataSource]
         */
        coEvery { postRepository.getPosts() } returns flowOf(MockingData.postsLocalDataSource)

        val postEntityList = MockingData.postsLocalDataSource.map { postDataSource ->
            postDataSourceToEntityMapper.map(input = postDataSource)
        }

        runBlocking {
            /**
             * WHEN [getPostsUseCase] is calling get posts.
             */
            val getPosts = getPostsUseCase.invoke()

            /**
             * THEN the local post list is emitted.
             */
            assertThat(getPosts.first()).isEqualTo(postEntityList)
        }
    }

    @Test
    fun `getPosts emits remote post list`() {
        /**
         * GIVEN [postRepository] is returning [MockingData.postsRemoteDataSource]
         */
        coEvery { postRepository.getPosts() } returns flowOf(MockingData.postsRemoteDataSource)

        val postEntityList = MockingData.postsRemoteDataSource.map { postDataSource ->
            postDataSourceToEntityMapper.map(input = postDataSource)
        }

        runBlocking {
            /**
             * WHEN [getPostsUseCase] is calling get posts.
             */
            val getPosts = getPostsUseCase.invoke()

            /**
             * THEN the remote post list is emitted.
             */
            assertThat(getPosts.first()).isEqualTo(postEntityList)
        }
    }
}