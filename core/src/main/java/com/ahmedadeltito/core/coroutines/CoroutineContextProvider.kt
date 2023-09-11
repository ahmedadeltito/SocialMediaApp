package com.ahmedadeltito.core.coroutines

import kotlin.coroutines.CoroutineContext

/**
 * Contract interface for [CoroutineContext] that can be implemented for real implementation like
 * [DefaultCoroutineContextProvider] and can be implementation for testing implementation if needed.
 */
interface CoroutineContextProvider {
    fun io(): CoroutineContext
    fun ui(): CoroutineContext
}