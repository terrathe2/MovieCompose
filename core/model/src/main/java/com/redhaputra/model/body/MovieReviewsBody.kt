package com.redhaputra.model.body

import androidx.annotation.Keep

/**
 * Movie review list body data model
 */
@Keep
data class MovieReviewsBody(
    val movieId: Int,
    val page: Int,
)