package com.ahmedadeltito.datasource.di

import android.app.Application
import com.ahmedadeltito.core.application.ApplicationModule
import com.ahmedadeltito.datasource.local.DatabaseManager
import com.ahmedadeltito.datasource.local.di.LocalModule
import com.ahmedadeltito.datasource.remote.di.RemoteModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Dagger Component that creates [RemoteModule], [LocalModule] and [CacheModule] with the help
 * of [ApplicationModule].
 */
@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        LocalModule::class,
        RemoteModule::class
    ]
)
interface DataSourceComponent {

    fun application(): Application

    fun local(): DatabaseManager

    fun remote(): Retrofit

    @Component.Factory
    interface Factory {
        fun create(applicationModule: ApplicationModule): DataSourceComponent
    }
}