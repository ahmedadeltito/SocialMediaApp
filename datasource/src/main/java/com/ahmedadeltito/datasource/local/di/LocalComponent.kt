package com.ahmedadeltito.datasource.local.di

import android.app.Application
import com.ahmedadeltito.core.application.ApplicationModule
import com.ahmedadeltito.datasource.local.DatabaseManager
import dagger.Component
import javax.inject.Singleton

/**
 * Dagger Component that creates [LocalModule] with the help of [ApplicationModule] .
 */
@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        LocalModule::class
    ]
)
interface LocalComponent {

    fun application(): Application

    fun local(): DatabaseManager
}