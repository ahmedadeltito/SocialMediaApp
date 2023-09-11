package com.ahmedadeltito.ui.home

import com.ahmedadeltito.ui.home.model.CommentUiModel
import com.ahmedadeltito.ui.home.model.PostUiModel

internal object MockingData {

    val postList = listOf(
        PostUiModel(id = "115", userId = "500", title = "Post 1", body = "Body 1"),
        PostUiModel(id = "3d8", userId = "200", title = "Post 2", body = "Body 2"),
        PostUiModel(id = "50s", userId = "600", title = "Post 3", body = "Body 3"),
        PostUiModel(id = "a18", userId = "300", title = "Post 4 ", body = "Body 4")
    )

    val commentList = listOf(
        CommentUiModel(
            id = "115",
            postId = "500",
            name = "Comment 1",
            email = "comment1@email.com",
            body = "Body 1"
        ),
        CommentUiModel(
            id = "3d8",
            postId = "200",
            name = "Comment 2",
            email = "comment2@email.com",
            body = "Body 2"
        ),
        CommentUiModel(
            id = "50s",
            postId = "600",
            name = "Comment 2",
            email = "comment2@email.com",
            body = "Body 2"
        ),
        CommentUiModel(
            id = "a18",
            postId = "300",
            name = "Comment 2",
            email = "comment2@email.com",
            body = "Body 2"
        ),
    )

    val post = PostUiModel(id = "115", userId = "500", title = "Post 1", body = "Body 1")
    val emptyPost = PostUiModel(id = "null", userId = "null")

}