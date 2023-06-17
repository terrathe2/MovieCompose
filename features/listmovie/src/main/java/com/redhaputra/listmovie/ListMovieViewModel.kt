package com.redhaputra.listmovie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.redhaputra.data.repositories.CommonDispatcherRepository
import com.redhaputra.listmovie.navigation.ListMovieDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * View model class that manage [ListMovieScreen] data
 */
@HiltViewModel
class ListMovieViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dispatcher: CommonDispatcherRepository
) : ViewModel() {
    val genre: String = checkNotNull(savedStateHandle[ListMovieDestination.genreArg])
}