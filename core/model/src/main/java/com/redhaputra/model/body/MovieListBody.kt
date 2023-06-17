package com.redhaputra.model.body

import androidx.annotation.Keep

/**
 * Movie list body data model
 */
@Keep
data class MovieListBody(
    val genre: String,
    val page: Int,
)