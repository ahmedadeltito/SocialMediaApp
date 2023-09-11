package com.ahmedadeltito.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedadeltito.datasource.local.models.PostLocal

/**
 * Data Access Objects for [PostLocal].
 */
@Dao
interface PostDao {

    @Query("SELECT * FROM post WHERE id = :id")
    suspend fun getPost(id: String): PostLocal?

    @Query("SELECT * FROM post")
    suspend fun getPosts(): List<PostLocal>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: PostLocal)
}