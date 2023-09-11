package com.ahmedadeltito.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedadeltito.datasource.local.models.CommentLocal

/**
 * Data Access Objects for [CommentLocal].
 */
@Dao
interface CommentDao {

    @Query("SELECT * FROM comment WHERE post_id = :postId")
    suspend fun getComments(postId: String): List<CommentLocal>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: CommentLocal)
}