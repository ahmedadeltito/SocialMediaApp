package com.ahmedadeltito.datasource.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Local model for posts.
 */
@Entity(tableName = "post")
data class PostLocal(

    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "user_id")
    val userId: String,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "body")
    val body: String? = null

)