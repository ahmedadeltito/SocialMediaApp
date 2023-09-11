package com.ahmedadeltito.feature.comment.usecase

import com.ahmedadeltito.feature.comment.MockingData
import com.ahmedadeltito.feature.comment.mapper.CommentDataSourceToEntityMapper
import com.ahmedadeltito.feature.comment.repository.CommentRepository
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for [GetCommentsUseCase]
 */
internal class GetCommentsUseCaseTests {

    private lateinit var getCommentsUseCase: GetCommentsUseCase

    private val commentDataSourceToEntityMapper = CommentDataSourceToEntityMapper()

    private val commentRepository = mockk<CommentRepository>(relaxed = true)

    @Before
    fun setup() {
        getCommentsUseCase = GetCommentsUseCase(
            repository = commentRepository,
            mapper = commentDataSourceToEntityMapper
        )
    }

    @Test
    fun `getComments emits local comment list`() {
        /**
         * GIVEN [commentRepository] is returning [MockingData.getCommentsLocalDataSource]
         */
        coEvery { commentRepository.getComments(postId = MockingData.POST_ID) } returns
                flowOf(MockingData.getCommentsLocalDataSource)

        val commentEntityList = MockingData.getCommentsLocalDataSource.map { commentDataSource ->
            commentDataSourceToEntityMapper.map(input = commentDataSource)
        }

        runBlocking {
            /**
             * WHEN [getCommentsUseCase] is calling get comments.
             */
            /**
             * WHEN [getCommentsUseCase] is calling get comments.
             */
            val getComments = getCommentsUseCase.invoke(postId = MockingData.POST_ID)

            /**
             * THEN the local comment list is emitted.
             */

            /**
             * THEN the local comment list is emitted.
             */
            Truth.assertThat(getComments.first()).isEqualTo(commentEntityList)
        }
    }

    @Test
    fun `getComments emits remote post list`() {
        /**
         * GIVEN [commentRepository] is returning [MockingData.getCommentsRemoteDataSource]
         */
        coEvery { commentRepository.getComments(postId = MockingData.POST_ID) } returns
                flowOf(MockingData.getCommentsRemoteDataSource)

        val commentEntityList = MockingData.getCommentsRemoteDataSource.map { commentDataSource ->
            commentDataSourceToEntityMapper.map(input = commentDataSource)
        }

        runBlocking {
            /**
             * WHEN [getCommentsUseCase] is calling get comments.
             */
            /**
             * WHEN [getCommentsUseCase] is calling get comments.
             */
            val getComments = getCommentsUseCase.invoke(postId = MockingData.POST_ID)

            /**
             * THEN the remote comment list is emitted.
             */

            /**
             * THEN the remote comment list is emitted.
             */
            Truth.assertThat(getComments.first()).isEqualTo(commentEntityList)
        }
    }
}