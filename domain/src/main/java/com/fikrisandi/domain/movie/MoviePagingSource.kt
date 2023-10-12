package com.fikrisandi.domain.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fikrisandi.model.remote.movie.Movie
import com.fikrisandi.repository.repository.movie.MovieRepository

class MoviePagingSource(private val repository: MovieRepository, private val option: Map<String, Any>) :
    PagingSource<Int, Movie>() {


    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        println("page = $page")
        return try {
            val genre = option["genre"]?.toString()?.toIntOrNull()
            val response = repository.getAllByGenre(genre ?: 0, page)
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