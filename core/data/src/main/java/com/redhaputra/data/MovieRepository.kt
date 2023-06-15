package com.redhaputra.data

import com.redhaputra.model.ListMovieGenreResponse
import com.redhaputra.network.adapter.NetworkResponse

/**
 * Interface of [MovieRepositoryImpl].
 */
interface MovieRepository {
    /**
     * Get Movie Genre list
     */
    suspend fun getMovieGenres(): NetworkResponse<ListMovieGenreResponse>
}