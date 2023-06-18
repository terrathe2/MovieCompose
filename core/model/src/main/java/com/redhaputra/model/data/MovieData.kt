package com.redhaputra.model.data

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

/**
 * Movie data model
 */
@Parcelize
@Keep
data class MovieData(
    val coverImg: String,
    val id: Int,
    val title: String,
    val overview: String,
    val posterImg: String,
    val vote: Double = 0.0
) : Parcelable
