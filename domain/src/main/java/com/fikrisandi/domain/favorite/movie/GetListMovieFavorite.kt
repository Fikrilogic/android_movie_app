package com.fikrisandi.domain.favorite.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fikrisandi.framework.usecase.FlowPagingUseCase
import com.fikrisandi.model.local.MovieFavoriteEntity
import com.fikrisandi.repository.repository.favorite.movie.MovieFavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListMovieFavorite @Inject constructor(private val repository: MovieFavoriteRepository) :
    FlowPagingUseCase<GetListMovieFavorite.Params, MovieFavoriteEntity>() {
    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, Any>
    )

    override fun execute(params: Params): Flow<PagingData<MovieFavoriteEntity>> {
        return Pager(
            config = params.pagingConfig,
            pagingSourceFactory = {
                MovieFavoritePagingSource(repository, params.options)
            }
        ).flow
    }

}