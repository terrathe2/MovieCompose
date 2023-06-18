package com.redhaputra.moviedetail.state

import com.redhaputra.model.response.ItemMovieTrailersResponse

/**
 * A sealed hierarchy describing the state of the movie trailers process.
 */
sealed interface MovieTrailerListState {
    /**
     * The movie trailers fetching process is still loading.
     */
    object Loading : MovieTrailerListState

    /**
     * The movie trailers process is loaded with the given data
     */
    data class Success(val trailer: ItemMovieTrailersResponse?) : MovieTrailerListState
}

/**
 * Data class for movie trailers ui State
 */
data class MovieDetailUIState(
    val trailerState: MovieTrailerListState?,
    val errorState: String?
)