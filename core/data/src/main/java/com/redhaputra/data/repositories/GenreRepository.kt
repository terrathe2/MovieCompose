package com.redhaputra.data.repositories

import com.redhaputra.model.response.ListMovieGenreResponse
import com.redhaputra.network.adapter.NetworkResponse

/**
 * Interface of GenreRepositoryImpl.
 */
interface GenreRepository {
    /**
     * Get Movie Genre list
     */
    suspend fun getMovieGenres(): NetworkResponse<ListMovieGenreResponse>
}