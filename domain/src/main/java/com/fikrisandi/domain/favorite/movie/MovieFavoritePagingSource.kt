package com.fikrisandi.domain.favorite.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fikrisandi.model.local.MovieFavoriteEntity
import com.fikrisandi.repository.repository.favorite.movie.MovieFavoriteRepository

class MovieFavoritePagingSource(
    private val repository: MovieFavoriteRepository,
    private val option: Map<String, Any>
) : PagingSource<Int, MovieFavoriteEntity>() {
    override fun getRefreshKey(state: PagingState<Int, MovieFavoriteEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieFavoriteEntity> {
        val page = params.key ?: 1
        val limit = (option["limit"] as? Int) ?: 10
        val offset = page * limit
        return try {
            val response = repository.get(limit, offset)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}