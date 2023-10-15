package com.fikrisandi.domain.favorite.movie

import android.util.Log
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
        val page = params.key ?: 0
        val limit = (option["limit"] as? Int) ?: 10
        val offset = page * limit
        Log.e("page", "load:  $limit & $offset", )
        return try {
            val response = repository.get(limit, offset)
            Log.e("data", "load: $response", )
            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Throwable) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}