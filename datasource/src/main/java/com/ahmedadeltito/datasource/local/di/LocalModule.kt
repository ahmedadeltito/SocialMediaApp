package com.ahmedadeltito.datasource.local.di

import android.app.Application
import com.ahmedadeltito.datasource.local.DatabaseManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger Module that provides [DatabaseManager] class after building it.
 */
@Module
class LocalModule {

    @Provides
    @Singleton
    fun provideDatabaseManager(application: Application): DatabaseManager =
        DatabaseManager.getInstance(application)
}