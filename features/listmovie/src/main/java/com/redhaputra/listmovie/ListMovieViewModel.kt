package com.redhaputra.listmovie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.redhaputra.data.repositories.CommonDispatcherRepository
import com.redhaputra.data.repositories.DiscoverRepository
import com.redhaputra.listmovie.component.ListMoviePagingSource
import com.redhaputra.listmovie.navigation.ListMovieDestination.genreIdArg
import com.redhaputra.listmovie.navigation.ListMovieDestination.genreNameArg
import com.redhaputra.model.data.MovieData
import com.redhaputra.model.response.ItemMoviesResponse
import com.redhaputra.model.response.asExternalData
import com.redhaputra.ui.utils.IntUtils.PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * View model class that manage [ListMovieRoute] data
 */
@HiltViewModel
class ListMovieViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: DiscoverRepository,
    private val dispatcher: CommonDispatcherRepository
) : ViewModel() {
    private val genreId: String = checkNotNull(savedStateHandle[genreIdArg])
    val genreName: String = checkNotNull(savedStateHandle[genreNameArg])

    private val pagingConfig = PagingConfig(PAGE_SIZE, initialLoadSize = PAGE_SIZE)

    val movieList: Flow<PagingData<MovieData>> =
        Pager(config = pagingConfig) {
            ListMoviePagingSource(genreId = genreId, repository = repository)
        }.flow
            .map { it.map(ItemMoviesResponse::asExternalData) }
            .flowOn(dispatcher.io)
            .cachedIn(viewModelScope)
}