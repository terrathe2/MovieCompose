package com.redhaputra.data.repositories.impl

import com.redhaputra.data.repositories.MovieRepository
import com.redhaputra.model.ListMovieReviewResponse
import com.redhaputra.network.adapter.NetworkResponse
import com.redhaputra.network.api.MovieService
import com.redhaputra.network.utils.NetworkConstants.CONNECTION_ERR
import com.redhaputra.network.utils.NetworkConstants.SERVER_ERR
import com.redhaputra.network.utils.NetworkConstants.UNKNOWN_ERR
import java.io.IOException
import javax.inject.Inject

/**
 * Repository module that handle movie API network response.
 */
class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService
) : MovieRepository {

    /**
     * Get movie reviews response method handling
     *
     * @param movieId Movie ID that needed for fetch Movie Reviews
     */
    override suspend fun getMovieReviews(movieId: Int): NetworkResponse<ListMovieReviewResponse> {
        val queryMap = mapOf(
            "language" to "en-US",
            "page" to 1
        )

        try {
            val request = service.getMovieReviews(id = movieId, query = queryMap)
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