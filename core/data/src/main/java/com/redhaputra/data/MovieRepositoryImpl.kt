package com.redhaputra.data

import com.redhaputra.model.ListMovieGenreResponse
import com.redhaputra.network.adapter.NetworkResponse
import com.redhaputra.network.api.MovieService
import java.io.IOException

/**
 * Repository module that handle movie API network response.
 */
class MovieRepositoryImpl(
    private val service: MovieService
) : MovieRepository {

    companion object {
        private const val CONNECTION_ERR = "Connection Error"
        private const val SERVER_ERR = "Server Error"
        private const val UNKNOWN_ERR = "Unknown Error"
    }

    override suspend fun getMovieGenres(): NetworkResponse<ListMovieGenreResponse> {
        try {
            val request = service.getMovieGenres()
            if (request.isSuccessful) {
                val body = request.body()
                return NetworkResponse.Success(body)
            }

            throw Exception(UNKNOWN_ERR)
        } catch (e: Exception) {
            if (e is IOException) {
                return NetworkResponse.Error(CONNECTION_ERR)
            }

            return NetworkResponse.Error(SERVER_ERR)
        }
    }
}