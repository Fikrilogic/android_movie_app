package com.fikrisandi.domain.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fikrisandi.model.remote.user.UserReview
import com.fikrisandi.repository.repository.movie.MovieRepository

class MovieUserPagingSource(
    private val repository: MovieRepository,
    private val option: Map<String, Any>
) : PagingSource<Int, UserReview>() {
    override fun getRefreshKey(state: PagingState<Int, UserReview>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserReview> {
        val page = params.key ?: 1
        return try {
            val id = option["id"]?.toString()
            val response = repository.getReview(id ?: "", page)
            LoadResult.Page(
                data = response.results ?: emptyList(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isNullOrEmpty()) null else page + 1
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }


}