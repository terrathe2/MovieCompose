package com.redhaputra.network.api

import com.redhaputra.model.ListMovieGenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Representation interface of the MovieDB Genre API endpoints.
 */
interface GenreService {
    /**
     * Get API Service for Movie Genre list
     */
    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("language") lang: String = "en"
    ): Response<ListMovieGenreResponse>
}