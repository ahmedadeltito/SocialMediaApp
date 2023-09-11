package com.ahmedadeltito.core.coroutines

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger Module that provides [CoroutineContextProvider] class.
 */
@Module
class CoroutineContextProviderModule {

    @Provides
    @Singleton
    fun providesCoroutineContext(): CoroutineContextProvider = DefaultCoroutineContextProvider()
}