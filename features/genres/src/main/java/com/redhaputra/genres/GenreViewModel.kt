package com.redhaputra.genres

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redhaputra.data.repositories.CommonDispatcherRepository
import com.redhaputra.data.repositories.GenreRepository
import com.redhaputra.genres.state.MovieGenreListState
import com.redhaputra.genres.state.MovieGenresUIState
import com.redhaputra.network.adapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * View model class that manage [GenreScreen] data
 */
@HiltViewModel
class GenreViewModel @Inject constructor(
    private val repository: GenreRepository,
    private val dispatcher: CommonDispatcherRepository
) : ViewModel() {

    // set default state of genre to empty
    private var loadedGenresLastState: MovieGenreListState = MovieGenreListState.Empty

    private val genresUiState =
        MutableStateFlow<MovieGenreListState>(MovieGenreListState.Loading)
    private val errorState = MutableStateFlow<String?>(null)

    val movieGenresUiState: StateFlow<MovieGenresUIState> =
        combine(
            genresUiState,
            errorState
        ) { genres, error ->
            MovieGenresUIState(genres, error)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieGenresUIState(
                genresState = MovieGenreListState.Loading,
                errorState = null
            )
        )

    init {
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch(dispatcher.io) {
            val response = repository.getMovieGenres()
            withContext(dispatcher.main) {
                when (response) {
                    is NetworkResponse.Success -> {
                        response.data?.genres?.let {
                            // change last state of genres only when api fetch success,
                            // so when error happen, saved state not changed
                            loadedGenresLastState = if (it.isEmpty()) {
                                MovieGenreListState.Empty
                            } else {
                                MovieGenreListState.Success(it)
                            }
                        }
                    }
                    is NetworkResponse.Error -> {
                        errorState.value = "Failed to get movie genres"
                    }
                }

                // change UI state value based on the last stated saved.
                // so when error happen after list loaded, loaded list still showing
                genresUiState.value = loadedGenresLastState
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