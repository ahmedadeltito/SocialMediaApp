package com.ahmedadeltito.feature.comment

import com.ahmedadeltito.datasource.local.models.CommentLocal
import com.ahmedadeltito.datasource.remote.models.CommentResponse
import com.ahmedadeltito.feature.comment.repository.model.CommentDataSource

internal object MockingData {

    val commentLocalList = listOf(
        CommentLocal(
            id = "115",
            postId = "500",
            name = "Comment 1",
            email = "cooment1@email.com",
            body = "Body 1"
        ),
        CommentLocal(
            id = "3d8",
            postId = "200",
            name = "Comment 2",
            email = "cooment2@email.com",
            body = "Body 2"
        ),
        CommentLocal(
            id = "50s",
            postId = "600",
            name = "Comment 3",
            email = "cooment3@email.com",
            body = "Body 3"
        ),
        CommentLocal(
            id = "a18",
            postId = "300",
            name = "Comment 4",
            email = "cooment4@email.com",
            body = "Body 4"
        )
    )
    val commentRemoteList = listOf(
        CommentResponse(
            id = "c2u",
            postId = "500",
            name = "Comment 11",
            email = "cooment11@email.com",
            body = "Body 11"
        ),
        CommentResponse(
            id = "dtu",
            postId = "200",
            name = "Comment 12",
            email = "cooment12@email.com",
            body = "Body 12"
        ),
        CommentResponse(
            id = "bga",
            postId = "600",
            name = "Comment 13",
            email = "cooment13@email.com",
            body = "Body 13"
        ),
        CommentResponse(
            id = "1do",
            postId = "300",
            name = "Comment 14",
            email = "cooment14@email.com",
            body = "Body 14"
        )
    )

    val getCommentsLocalDataSource = listOf(
        CommentDataSource(
            id = "115",
            postId = "500",
            name = "Comment 1",
            email = "cooment1@email.com",
            body = "Body 1"
        ),
        CommentDataSource(
            id = "3d8",
            postId = "200",
            name = "Comment 2",
            email = "cooment2@email.com",
            body = "Body 2"
        ),
        CommentDataSource(
            id = "50s",
            postId = "600",
            name = "Comment 3",
            email = "cooment3@email.com",
            body = "Body 3"
        ),
        CommentDataSource(
            id = "a18",
            postId = "300",
            name = "Comment 4",
            email = "cooment4@email.com",
            body = "Body 4"
        )
    )
    val getCommentsRemoteDataSource = listOf(
        CommentDataSource(
            id = "c2u",
            postId = "500",
            name = "Comment 11",
            email = "cooment11@email.com",
            body = "Body 11"
        ),
        CommentDataSource(
            id = "dtu",
            postId = "200",
            name = "Comment 12",
            email = "cooment12@email.com",
            body = "Body 12"
        ),
        CommentDataSource(
            id = "bga",
            postId = "600",
            name = "Comment 13",
            email = "cooment13@email.com",
            body = "Body 13"
        ),
        CommentDataSource(
            id = "1do",
            postId = "300",
            name = "Comment 14",
            email = "cooment14@email.com",
            body = "Body 14"
        )
    )

    const val POST_ID = "500"

}