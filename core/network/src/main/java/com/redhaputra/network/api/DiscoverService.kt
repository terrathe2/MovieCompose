package com.redhaputra.network.api

import com.redhaputra.model.response.ListMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Representation interface of the MovieDB Discover API endpoints.
 */
interface DiscoverService {
    /**
     * Get API Service for Movie list by genre
     */
    @GET("discover/movie")
    @JvmSuppressWildcards
    suspend fun getMovies(
        @QueryMap(encoded = true) query: Map<String, Any>
    ): Response<ListMovieResponse>
}