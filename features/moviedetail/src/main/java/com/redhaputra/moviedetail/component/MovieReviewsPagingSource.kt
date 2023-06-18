package com.redhaputra.moviedetail.component

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.redhaputra.data.repositories.MovieRepository
import com.redhaputra.model.body.MovieReviewsBody
import com.redhaputra.model.response.ItemMovieReviewsResponse
import com.redhaputra.network.adapter.NetworkResponse
import com.redhaputra.ui.utils.IntUtils

/**
 * It is used to load pages of Movie Review list data.
 */
class MovieReviewsPagingSource(
    private val movieId: Int,
    private val repository: MovieRepository
) : PagingSource<Int, ItemMovieReviewsResponse>() {
    override fun getRefreshKey(state: PagingState<Int, ItemMovieReviewsResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemMovieReviewsResponse> {
        val pageNumber = params.key ?: 1
        // Since 1 is the lowest page number, return null to signify no more pages should
        // be loaded before it.
        val prevKey = if (pageNumber <= 1) null else pageNumber - 1
        val param = MovieReviewsBody(page = pageNumber, movieId = movieId)
        return when (val response = repository.getMovieReviews(param)) {
            is NetworkResponse.Success -> {
                val totalPages = response.data?.totalPages ?: 1
                val data = response.data?.results ?: listOf()
                val nextKey =
                    if (data.isNotEmpty() && pageNumber < totalPages && pageNumber < IntUtils.LIST_MAX_PAGE) {
                        pageNumber + 1
                    } else {
                        null
                    }
                LoadResult.Page(data, prevKey, nextKey)
            }
            is NetworkResponse.Error -> LoadResult.Error(Throwable("Failed to get reviews"))
        }
    }
}