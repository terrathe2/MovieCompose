package com.redhaputra.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redhaputra.data.repositories.CommonDispatcherRepository
import com.redhaputra.data.repositories.MovieRepository
import com.redhaputra.model.data.MovieData
import com.redhaputra.moviedetail.state.MovieDetailUIState
import com.redhaputra.moviedetail.state.MovieTrailerListState
import com.redhaputra.network.adapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * View model class that manage [MovieDetailRoute] data
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val dispatcher: CommonDispatcherRepository
) : ViewModel() {

    private val _movieData = MutableStateFlow<MovieData?>(null)
    val movieData: StateFlow<MovieData?> = _movieData.asStateFlow()

    var loadedTrailersUiState: MovieTrailerListState? = null

    private val trailerUiState =
        MutableStateFlow<MovieTrailerListState?>(MovieTrailerListState.Loading)
    private val errorState = MutableStateFlow<String?>(null)

    val movieDetailUIState: StateFlow<MovieDetailUIState> =
        combine(
            trailerUiState,
            errorState
        ) { trailer, error ->
            MovieDetailUIState(trailer, error)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieDetailUIState(
                trailerState = MovieTrailerListState.Loading,
                errorState = null
            )
        )

    /**
     * Save movie data sent from previous screen
     */
    fun setMovieData(data: MovieData) {
        if (data != _movieData.value) {
            _movieData.value = data
        }
    }

    /**
     * Handle get movie trailers response from API
     */
    fun getMovieTrailers(movieId: Int) {
        if (trailerUiState.value !is MovieTrailerListState.Loading) {
            trailerUiState.value = MovieTrailerListState.Loading
        }

        viewModelScope.launch(dispatcher.io) {
            val response = repository.getMovieTrailers(movieId)
            withContext(dispatcher.main) {
                when (response) {
                    is NetworkResponse.Success -> {
                        loadedTrailersUiState = response.data?.results?.let { list ->
                            val data = list.findLast {
                                it.official == true &&
                                it.site?.lowercase() == "youtube" &&
                                it.type?.lowercase() == "trailer"
                            }
                            MovieTrailerListState.Success(data)
                        }
                    }
                    is NetworkResponse.Error -> {
                        errorState.value = "Failed to get movie trailers"
                    }
                }

                // change UI state value based on the last stated saved.
                // so when error happen after list loaded, loaded list still showing
                trailerUiState.value = loadedTrailersUiState
            }
        }
    }

    /**
     * Handle reset error message, so snackbar will not showed everytime
     */
    fun resetErrorMessage() {
        errorState.value = null
    }
}