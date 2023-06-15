package com.redhaputra.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Review List Response data model
 */
@Keep
@JsonClass(generateAdapter = true)
data class ListMovieReviewResponse (
    @Json(name = "page")
    val page: Int?,
    @Json(name = "results")
    val results: List<ItemMovieReviewsResponse>?,
    @Json(name = "total_pages")
    val totalPages: Int?,
    @Json(name = "total_results")
    val totalResults: Int?
)