package com.ahmedadeltito.core.application

import android.app.Application
import dagger.Component
import javax.inject.Singleton

/**
 * Dagger Component that creates [ApplicationModule].
 */
@Singleton
@Component(
    modules = [
        ApplicationModule::class
    ]
)
interface ApplicationComponent {

    fun application(): Application

    @Component.Factory
    interface Factory {
        fun create(applicationModule: ApplicationModule): ApplicationComponent
    }
}