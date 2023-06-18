package com.redhaputra.model.response

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Movie Trailer List Response data model
 */
@Keep
@JsonClass(generateAdapter = true)
data class ListMovieTrailerResponse(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "results")
    val results: List<ItemMovieTrailersResponse>?,
)