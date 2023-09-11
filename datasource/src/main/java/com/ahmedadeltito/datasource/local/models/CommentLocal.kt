package com.ahmedadeltito.datasource.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Local model for comments.
 */
@Entity(tableName = "comment")
data class CommentLocal(

    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "post_id")
    val postId: String,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "email")
    val email: String? = null,

    @ColumnInfo(name = "body")
    val body: String? = null

)