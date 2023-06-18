package com.redhaputra.network.api

import com.redhaputra.model.response.ListMovieReviewResponse
import com.redhaputra.model.response.ListMovieTrailerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Representation interface of the MovieDB Movie API endpoints.
 */
interface MovieService {
    /**
     * Get API Service for Movie Trailers
     */
    @GET("movie/{id}/videos")
    suspend fun getMovieTrailers(
        @Path("id") id: Int,
        @Query("language") language: String
    ): Response<ListMovieTrailerResponse>

    /**
     * Get API Service for Movie Reviews
     */
    @GET("movie/{id}/reviews")
    @JvmSuppressWildcards
    suspend fun getMovieReviews(
        @Path("id") id: Int,
        @QueryMap query: Map<String, Any>
    ): Response<ListMovieReviewResponse>
}