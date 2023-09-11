package com.ahmedadeltito.core.coroutines

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers

/**
 * Default implementation for the [CoroutineContextProvider]
 */
class DefaultCoroutineContextProvider : CoroutineContextProvider {
    override fun io(): CoroutineContext = Dispatchers.IO
    override fun ui(): CoroutineContext = Dispatchers.Main
}