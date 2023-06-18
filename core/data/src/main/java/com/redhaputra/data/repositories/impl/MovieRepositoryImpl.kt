package com.redhaputra.data.repositories.impl

import com.redhaputra.data.repositories.MovieRepository
import com.redhaputra.model.body.MovieReviewsBody
import com.redhaputra.model.response.ListMovieReviewResponse
import com.redhaputra.model.response.ListMovieTrailerResponse
import com.redhaputra.network.adapter.NetworkResponse
import com.redhaputra.network.api.MovieService
import com.redhaputra.network.utils.NetworkConstants.UNKNOWN_ERR
import java.io.IOException
import javax.inject.Inject

/**
 * Repository module that handle movie API network response.
 */
class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService
) : MovieRepository {
    override suspend fun getMovieTrailers(movieId: Int): NetworkResponse<ListMovieTrailerResponse> {
        try {
            val request = service.getMovieTrailers(id = movieId, language = "en-US")
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

    override suspend fun getMovieReviews(params: MovieReviewsBody): NetworkResponse<ListMovieReviewResponse> {
        val queryMap = mapOf(
            "language" to "en-US",
            "page" to params.page
        )

        try {
            val request = service.getMovieReviews(id = params.movieId, query = queryMap)
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