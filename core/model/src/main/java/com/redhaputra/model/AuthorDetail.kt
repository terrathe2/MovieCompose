package com.redhaputra.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Detail of Author data model
 */
@Keep
@JsonClass(generateAdapter = true)
data class AuthorDetail(
    @Json(name = "name")
    val name: String?,
    @Json(name = "username")
    val username: String?,
    @Json(name = "avatar_path")
    val avatar: String?,
    @Json(name = "rating")
    val rating: Int?,
)
