package com.redhaputra.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Item of Movie Genre List Response data model
 */
@Keep
@JsonClass(generateAdapter = true)
data class ItemMovieGenresResponse(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)