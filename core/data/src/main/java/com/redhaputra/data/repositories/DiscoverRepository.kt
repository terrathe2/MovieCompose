package com.redhaputra.data.repositories

import com.redhaputra.model.body.MovieListBody
import com.redhaputra.model.response.ListMovieResponse
import com.redhaputra.network.adapter.NetworkResponse

/**
 * Interface of DiscoverRepositoryImpl.
 */
interface DiscoverRepository {
    /**
     * Get Movie list
     */
    suspend fun getMovies(params: MovieListBody): NetworkResponse<ListMovieResponse>
}