package com.redhaputra.network.api

import com.redhaputra.model.response.ListMovieReviewResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Representation interface of the MovieDB Movie API endpoints.
 */
interface MovieService {
    /**
     * Get API Service for Movie Review
     */
    @GET("movie/{id}/reviews")
    @JvmSuppressWildcards
    suspend fun getMovieReviews(
        @Path("id") id: Int,
        @QueryMap query: Map<String, Any>
    ): Response<ListMovieReviewResponse>
}