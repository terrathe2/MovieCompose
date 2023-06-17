package com.redhaputra.genres.state

import com.redhaputra.model.response.ItemMovieGenresResponse

/**
 * A sealed hierarchy describing the state of the movie genres process.
 */
sealed interface MovieGenreListState {
    /**
     * The movie genres data is empty.
     */
    object Empty : MovieGenreListState

    /**
     * The movie genres process is loaded with the given data
     */
    data class Success(val genres: List<ItemMovieGenresResponse>) : MovieGenreListState
}

/**
 * Data class for movie genres ui State
 */
data class MovieGenresUIState(
    val genresState: MovieGenreListState,
    val errorState: String?
)