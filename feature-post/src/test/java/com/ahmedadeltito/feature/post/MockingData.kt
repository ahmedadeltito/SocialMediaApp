package com.ahmedadeltito.feature.post

import com.ahmedadeltito.datasource.local.models.PostLocal
import com.ahmedadeltito.datasource.remote.models.PostResponse
import com.ahmedadeltito.feature.post.repository.model.PostDataSource

internal object MockingData {

    val postLocalList = listOf(
        PostLocal(id = "115", userId = "500", title = "Post 1", body = "Body 1"),
        PostLocal(id = "3d8", userId = "200", title = "Post 2", body = "Body 2"),
        PostLocal(id = "50s", userId = "600", title = "Post 3", body = "Body 3"),
        PostLocal(id = "a18", userId = "300", title = "Post 4 ", body = "Body 4"),
    )
    val postRemoteList = listOf(
        PostResponse(id = "c2u", userId = "500", title = "Post 10", body = "Body 10"),
        PostResponse(id = "dtu", userId = "200", title = "Post 11", body = "Body 11"),
        PostResponse(id = "bga", userId = "600", title = "Post 12", body = "Body 12"),
        PostResponse(id = "1do", userId = "300", title = "Post 13", body = "Body 13")
    )

    val postLocal =
        PostLocal(id = "115", userId = "500", title = "Post 1", body = "Body 1")
    val postRemote =
        PostResponse(id = "115", userId = "510", title = "Post 30", body = "Body 30")

    val postsLocalDataSource = listOf(
        PostDataSource(id = "115", userId = "500", title = "Post 1", body = "Body 1"),
        PostDataSource(id = "3d8", userId = "200", title = "Post 2", body = "Body 2"),
        PostDataSource(id = "50s", userId = "600", title = "Post 3", body = "Body 3"),
        PostDataSource(id = "a18", userId = "300", title = "Post 4", body = "Body 4"),
    )
    val postsRemoteDataSource = listOf(
        PostDataSource(id = "c2u", userId = "500", title = "Post 10", body = "Body 10"),
        PostDataSource(id = "dtu", userId = "200", title = "Post 11", body = "Body 11"),
        PostDataSource(id = "bga", userId = "600", title = "Post 12", body = "Body 12"),
        PostDataSource(id = "1do", userId = "300", title = "Post 13", body = "Body 13")
    )

    val postDataSource =
        PostDataSource(id = "115", userId = "Post 1", title = "500", body = "Body 1")
    val emptyPostDataSource =
        PostDataSource(id = "115", userId = "Post 1", body = "Body 1")

}