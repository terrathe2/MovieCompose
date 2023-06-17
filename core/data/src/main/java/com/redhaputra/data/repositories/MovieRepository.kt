package com.redhaputra.data.repositories

import com.redhaputra.model.response.ListMovieReviewResponse
import com.redhaputra.network.adapter.NetworkResponse

/**
 * Interface of MovieRepositoryImpl.
 */
interface MovieRepository {
    suspend fun getMovieReviews(movieId: Int): NetworkResponse<ListMovieReviewResponse>
}