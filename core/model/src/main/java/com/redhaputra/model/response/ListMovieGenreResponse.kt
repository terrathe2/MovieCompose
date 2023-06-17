package com.redhaputra.model.response

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Movie Genres Response data model
 */
@Keep
@JsonClass(generateAdapter = true)
data class ListMovieGenreResponse(
    @Json(name = "genres")
    val genres: List<ItemMovieGenresResponse>?
)