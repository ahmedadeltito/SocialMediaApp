package com.ahmedadeltito.core.exceptions


/**
 * All the web services and network exceptions should be placed or added here in this sealed class.
 */
sealed class NetworkException(error: Throwable) : RuntimeException(error) {
    data class NoNetworkException(internal val error: Throwable) : NetworkException(error = error)
    data class ServerUnreachableException(internal val error: Throwable) : NetworkException(error = error)
    data class NotFoundException(internal val notFound: String) :
        NetworkException(error = Throwable(message = notFound))

    data class UnknownException(internal val error: Throwable) : NetworkException(error = error)
}