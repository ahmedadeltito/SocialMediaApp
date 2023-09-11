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
 * Unit tests for [GetPostUseCase]
 */
internal class GetPostUseCaseTests {

    private lateinit var getPostUseCase: GetPostUseCase

    private val postDataSourceToEntityMapper = PostDataSourceToEntityMapper()

    private val postRepository = mockk<PostRepository>(relaxed = true)

    @Before
    fun setup() {
        getPostUseCase = GetPostUseCase(
            repository = postRepository,
            mapper = postDataSourceToEntityMapper
        )
    }

    @Test
    fun `getPost emits local post`() {
        /**
         * GIVEN [postRepository] is returning [MockingData.postDataSource]
         */
        coEvery { postRepository.getPost(id = MockingData.postDataSource.id) } returns flowOf(
            MockingData.postDataSource
        )

        val postEntity = postDataSourceToEntityMapper.map(input = MockingData.postDataSource)

        runBlocking {
            /**
             * WHEN [getPostUseCase] is calling get Posts.
             */
            val getPosts = getPostUseCase.invoke(MockingData.emptyPostDataSource.id)

            /**
             * THEN the local Post list is emitted.
             */
            assertThat(getPosts.first()).isEqualTo(postEntity)
        }
    }

    @Test
    fun `getPost emits empty local post`() {
        /**
         * GIVEN [postRepository] is returning [MockingData.postDataSource]
         */
        coEvery { postRepository.getPost(id = MockingData.emptyPostDataSource.id) } returns flowOf(
            MockingData.emptyPostDataSource
        )

        val postEntity = postDataSourceToEntityMapper.map(input = MockingData.emptyPostDataSource)

        runBlocking {
            /**
             * WHEN [getPostUseCase] is calling get posts.
             */
            val getPosts = getPostUseCase.invoke(id = MockingData.emptyPostDataSource.id)

            /**
             * THEN the local post list is emitted.
             */
            assertThat(getPosts.first()).isEqualTo(postEntity)
        }
    }
}