package com.redhaputra.network.adapter

/**
 * Generic network response
 * @param T success body response
 */
sealed class NetworkResponse<out T : Any> {

    /**
     * Handle resource success
     */
    class Success<out T : Any>(val data: T?) : NetworkResponse<T>()

    /**
     * Handle resource error
     */
    object Error : NetworkResponse<Nothing>()
}