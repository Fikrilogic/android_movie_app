package com.fikrisandi.domain.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fikrisandi.framework.usecase.FlowPagingUseCase
import com.fikrisandi.model.remote.movie.Movie
import com.fikrisandi.repository.repository.movie.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieBySearch @Inject constructor(private val repository: MovieRepository): FlowPagingUseCase<GetMovieBySearch.Params, Movie>() {
    data class Params(
        val config: PagingConfig,
        val option: Map<String, Any>
    )

    override fun execute(params: Params): Flow<PagingData<Movie>> {
        return Pager(
            config = params.config,
            pagingSourceFactory = { MoviePagingSearchSource(repository, params.option) }
        ).flow.flowOn(Dispatchers.IO)
    }
}