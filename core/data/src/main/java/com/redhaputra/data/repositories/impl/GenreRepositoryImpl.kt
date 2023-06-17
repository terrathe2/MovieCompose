package com.redhaputra.data.repositories.impl

import com.redhaputra.data.repositories.GenreRepository
import com.redhaputra.model.response.ListMovieGenreResponse
import com.redhaputra.network.adapter.NetworkResponse
import com.redhaputra.network.api.GenreService
import com.redhaputra.network.utils.NetworkConstants.UNKNOWN_ERR
import java.io.IOException
import javax.inject.Inject

/**
 * Repository module that handle genre API network response.
 */
class GenreRepositoryImpl @Inject constructor(
    private val service: GenreService
) : GenreRepository {

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
                return NetworkResponse.Error
            }

            return NetworkResponse.Error
        }
    }
}