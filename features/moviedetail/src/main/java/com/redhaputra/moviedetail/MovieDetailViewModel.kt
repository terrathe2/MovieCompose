package com.redhaputra.moviedetail

import androidx.lifecycle.ViewModel
import com.redhaputra.data.repositories.CommonDispatcherRepository
import com.redhaputra.data.repositories.MovieRepository
import com.redhaputra.model.data.MovieData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    /**
     * Save movie data sent from previous screen
     */
    fun setMovieData(data: MovieData) {
        if (data != _movieData.value) {
            _movieData.value = data
        }
    }
}