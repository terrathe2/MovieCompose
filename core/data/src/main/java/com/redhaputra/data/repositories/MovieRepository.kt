package com.redhaputra.data.repositories

import com.redhaputra.model.response.ListMovieReviewResponse
import com.redhaputra.model.response.ListMovieTrailerResponse
import com.redhaputra.network.adapter.NetworkResponse

/**
 * Interface of MovieRepositoryImpl.
 */
interface MovieRepository {
    /**
     * Get Movie Trailer list
     */
    suspend fun getMovieTrailers(movieId: Int): NetworkResponse<ListMovieTrailerResponse>
    /**
     * Get Movie Review list
     */
    suspend fun getMovieReviews(movieId: Int): NetworkResponse<ListMovieReviewResponse>
}