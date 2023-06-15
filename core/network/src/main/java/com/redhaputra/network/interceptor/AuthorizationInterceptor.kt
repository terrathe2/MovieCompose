package com.redhaputra.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Interceptor that used to intercept network to pass auth token
 */
class AuthorizationInterceptor : Interceptor {

    companion object {
        // this shouldn't be hardcoded and uploaded to git, but it's for easy access
        private const val token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjZTA1MmYzNmRmOTNlZDc4ZGRlZW" +
                "Y0NWFlODZkYjQwYyIsInN1YiI6IjY0Nzc1YWVhMjU1ZGJhMDE0YTA3ZDFmMCIsInNjb3BlcyI6WyJh" +
                "cGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.3NS_M5V4cGdTyVIsbUYrrWoyaq-0rlYrAGSjk32tc8A"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val mainRequest = requestBuilder(chain.request())
        return chain.proceed(mainRequest)
    }

    private fun requestBuilder(mainRequest: Request): Request = mainRequest.newBuilder()
        .header("Authorization", "Bearer $token")
        .header("Content-Type", "application/json")
        .method(mainRequest.method(), mainRequest.body())
        .build()
}