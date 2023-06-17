package com.redhaputra.data.repositories.impl

import com.redhaputra.data.repositories.DiscoverRepository
import com.redhaputra.model.body.MovieListBody
import com.redhaputra.model.response.ListMovieResponse
import com.redhaputra.network.adapter.NetworkResponse
import com.redhaputra.network.api.DiscoverService
import com.redhaputra.network.utils.NetworkConstants
import java.io.IOException
import javax.inject.Inject

/**
 * Repository module that handle discover API network response.
 */
class DiscoverRepositoryImpl @Inject constructor(
    private val service: DiscoverService
) : DiscoverRepository {

    override suspend fun getMovies(params: MovieListBody): NetworkResponse<ListMovieResponse> {
        val queryMap = mapOf(
            "include_adult" to false,
            "include_video" to false,
            "language" to "en-US",
            "page" to params.page,
            "region" to "ID",
            "sort_by" to "popularity.desc",
            "with_genres" to params.genre
        )

        try {
            val request = service.getMovies(queryMap)
            if (request.isSuccessful) {
                val body = request.body()
                return NetworkResponse.Success(body)
            }

            throw Exception(NetworkConstants.UNKNOWN_ERR)
        } catch (e: Exception) {
            if (e is IOException) {
                return NetworkResponse.Error
            }

            return NetworkResponse.Error
        }
    }
}