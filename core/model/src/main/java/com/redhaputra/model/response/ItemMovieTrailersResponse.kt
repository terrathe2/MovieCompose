package com.redhaputra.model.response

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Item of Movie Trailer List Response data model
 */
@Keep
@JsonClass(generateAdapter = true)
data class ItemMovieTrailersResponse(
    @Json(name = "id")
    val id: String?,
    @Json(name = "key")
    val key: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "official")
    val official: Boolean?,
    @Json(name = "site")
    val site: String?,
    @Json(name = "type")
    val type: String?,
)