package com.redhaputra.model.data

import androidx.annotation.Keep

/**
 * Movie data model
 */
@Keep
data class MovieData(
    val coverImg: String,
    val id: Int,
    val title: String,
    val overview: String,
    val posterImg: String,
    val vote: Double = 0.0
)
