package com.ahmedadeltito.datasource.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ahmedadeltito.datasource.local.dao.CommentDao
import com.ahmedadeltito.datasource.local.dao.PostDao
import com.ahmedadeltito.datasource.local.models.PostLocal
import com.ahmedadeltito.datasource.local.models.CommentLocal

/**
 * Base class for all Room databases in our application.
 * For know, we only have one room entity which is [PostLocal] and [CommentLocal].
 */
@Database(
    entities = [
        PostLocal::class,
        CommentLocal::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun postDao(): PostDao

    abstract fun commentDao(): CommentDao

    companion object {

        @Synchronized
        fun getInstance(application: Application): DatabaseManager {
            return Room.databaseBuilder(
                application.applicationContext,
                DatabaseManager::class.java,
                "social-media-app.db"
            ).fallbackToDestructiveMigration().build()
        }
    }
}