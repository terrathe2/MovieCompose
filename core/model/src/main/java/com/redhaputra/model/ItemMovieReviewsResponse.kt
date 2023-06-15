package com.redhaputra.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Item of Movie Review List Response data model
 */
@Keep
@JsonClass(generateAdapter = true)
data class ItemMovieReviewsResponse(
    @Json(name = "author")
    val author: String?,
    @Json(name = "author_details")
    val authorDetail: AuthorDetail?,
    @Json(name = "content")
    val content: String?,
    @Json(name = "id")
    val id: String?
)
