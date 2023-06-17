package com.redhaputra.ui.utils

/**
 * Define string util for any modified string
 */

object StringUtils {

    const val X_ICON = "X"

    /**
     * Combine poster image response to base original image url
     */
    fun String.toPosterImg(): String = "https://image.tmdb.org/t/p/original${this}"

    /**
     * Combine cover image response to base original image url
     */
    fun String.toCoverImg(): String = "https://image.tmdb.org/t/p/w500${this}"
}