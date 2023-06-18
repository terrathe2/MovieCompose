package com.redhaputra.listmovie.component

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.redhaputra.data.repositories.DiscoverRepository
import com.redhaputra.model.body.MovieListBody
import com.redhaputra.model.response.ItemMoviesResponse
import com.redhaputra.network.adapter.NetworkResponse
import com.redhaputra.ui.utils.IntUtils.LIST_MAX_PAGE

/**
 * It is used to load pages of Movie List data.
 */
class ListMoviePagingSource(
    private val genreId: String,
    private val repository: DiscoverRepository
) : PagingSource<Int, ItemMoviesResponse>() {

    override fun getRefreshKey(state: PagingState<Int, ItemMoviesResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemMoviesResponse> {
        val pageNumber = params.key ?: 1
        // Since 1 is the lowest page number, return null to signify no more pages should
        // be loaded before it.
        val prevKey = if (pageNumber <= 1) null else pageNumber - 1
        val param = MovieListBody(page = pageNumber, genreId = genreId)
        return when (val response = repository.getMovies(param)) {
            is NetworkResponse.Success -> {
                val totalPages = response.data?.totalPages ?: 1
                val data = response.data?.results ?: listOf()
                val nextKey =
                    if (data.isNotEmpty() && pageNumber < totalPages && pageNumber < LIST_MAX_PAGE) {
                        pageNumber + 1
                    } else {
                        null
                    }
                LoadResult.Page(data, prevKey, nextKey)
            }
            is NetworkResponse.Error -> LoadResult.Error(Throwable("Failed to get movie"))
        }
    }
}