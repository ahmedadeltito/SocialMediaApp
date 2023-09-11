package com.ahmedadeltito.datasource.remote.di

import dagger.Component
import javax.inject.Singleton
import retrofit2.Retrofit

/**
 * Dagger Component that creates [RemoteModule].
 */
@Singleton
@Component(
    modules = [
        RemoteModule::class
    ]
)
interface RemoteComponent {

    fun retrofit(): Retrofit

    @Component.Factory
    interface Factory {
        fun create(remoteModule: RemoteModule): RemoteComponent
    }
}