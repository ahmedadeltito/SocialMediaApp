package com.ahmedadeltito.feature.comment.repository

import com.ahmedadeltito.datasource.local.dao.CommentDao
import com.ahmedadeltito.feature.comment.MockingData
import com.ahmedadeltito.feature.comment.apiservice.CommentAPIService
import com.ahmedadeltito.feature.comment.mapper.CommentLocalToDataSourceMapper
import com.ahmedadeltito.feature.comment.mapper.CommentResponseToDataSourceMapper
import com.ahmedadeltito.feature.comment.mapper.CommentResponseToLocalMapper
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for [CommentRepository]
 */
internal class CommentRepositoryTests {

    private lateinit var commentRepository: CommentRepository

    private val commentResponseToDataSourceMapper = CommentResponseToDataSourceMapper()
    private val commentResponseToLocalMapper = CommentResponseToLocalMapper()
    private val commentLocalToDataSourceMapper = CommentLocalToDataSourceMapper()

    private val local = mockk<CommentDao>(relaxed = true)
    private val remote = mockk<CommentAPIService>(relaxed = true)

    @Before
    fun setup() {
        commentRepository = CommentRepository(
            local = local,
            remote = remote,
            commentResponseToDataSourceMapper = commentResponseToDataSourceMapper,
            commentResponseToLocalMapper = commentResponseToLocalMapper,
            commentLocalToDataSourceMapper = commentLocalToDataSourceMapper
        )
    }

    @Test
    fun `getComments emits local comment list first then remote comment list`() {
        /**
         * GIVEN
         * - [local] is returning [MockingData.commentLocalList]
         * - [remote] is returning [MockingData.commentRemoteList]
         */
        coEvery { local.getComments(postId = MockingData.POST_ID) } returns MockingData.commentLocalList
        coEvery { remote.getComments(postId = MockingData.POST_ID) } returns MockingData.commentRemoteList

        val commentLocalDataSourceList = MockingData.commentLocalList.map { commentLocal ->
            commentLocalToDataSourceMapper.map(input = commentLocal)
        }
        val commentRemoteDataSourceList = MockingData.commentRemoteList.map { commentResponse ->
            commentResponseToDataSourceMapper.map(input = commentResponse)
        }

        runBlocking {
            /**
             * WHEN [commentRepository] is calling get comments.
             */
            /**
             * WHEN [commentRepository] is calling get comments.
             */
            val getComments = commentRepository.getComments(postId = MockingData.POST_ID)

            /**
             * THEN the local comment list is emitted first.
             * AND the remote comment list is emitted second.
             */

            /**
             * THEN the local comment list is emitted first.
             * AND the remote comment list is emitted second.
             */
            Truth.assertThat(getComments.first()).isEqualTo(commentLocalDataSourceList)
            Truth.assertThat(getComments.last()).isEqualTo(commentRemoteDataSourceList)
        }
    }

    @Test
    fun `getComments emits remote comment list only in case of empty local comment list`() {
        /**
         * GIVEN
         * - [local] is returning [emptyList]
         * - [remote] is returning [MockingData.commentRemoteList]
         */
        coEvery { local.getComments(postId = MockingData.POST_ID) } returns emptyList()
        coEvery { remote.getComments(postId = MockingData.POST_ID) } returns MockingData.commentRemoteList

        val commentRemoteDataSourceList = MockingData.commentRemoteList.map { commentResponse ->
            commentResponseToDataSourceMapper.map(input = commentResponse)
        }

        runBlocking {
            /**
             * WHEN [commentRepository] is calling get comments.
             */
            /**
             * WHEN [commentRepository] is calling get comments.
             */
            val getComments = commentRepository.getComments(postId = MockingData.POST_ID)

            /**
             * THEN the local comment list is emitted only.
             */

            /**
             * THEN the local comment list is emitted only.
             */
            Truth.assertThat(getComments.first()).isEqualTo(commentRemoteDataSourceList)
        }
    }
}