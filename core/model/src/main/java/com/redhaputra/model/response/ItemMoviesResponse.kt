package com.redhaputra.model.response

import androidx.annotation.Keep
import com.redhaputra.model.data.MovieData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Item of Movie List Response data model
 */
@Keep
@JsonClass(generateAdapter = true)
data class ItemMoviesResponse(
    @Json(name = "backdrop_path")
    val coverImg: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "poster_path")
    val posterImg: String?,
    @Json(name = "original_title")
    val title: String?,
    @Json(name = "vote_average")
    val vote: Double?,
)

/**
 * Method extended from [ItemMoviesResponse]
 * to map response data to [MovieData]
 */
fun ItemMoviesResponse.asExternalData(): MovieData =
    MovieData(
        id = id ?: 0,
        coverImg = coverImg ?: "",
        posterImg = posterImg ?: "",
        overview = overview ?: "-",
        title = title ?: "-",
        vote = vote ?: 0.0
    )