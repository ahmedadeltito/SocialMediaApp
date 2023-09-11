package com.ahmedadeltito.core.application

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger Module that provides [Application] class.
 */
@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application
}