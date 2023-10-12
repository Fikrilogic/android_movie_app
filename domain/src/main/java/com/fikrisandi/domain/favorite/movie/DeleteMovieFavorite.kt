package com.fikrisandi.domain.favorite.movie

import com.fikrisandi.framework.usecase.LocalUseCase
import com.fikrisandi.model.local.MovieFavoriteEntity
import com.fikrisandi.repository.repository.favorite.movie.MovieFavoriteRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class DeleteMovieFavorite @Inject constructor(private val repository: MovieFavoriteRepository) :
    LocalUseCase<DeleteMovieFavorite.Params, Unit>() {
    data class Params(
        val data: MovieFavoriteEntity
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        try {
            repository.delete(params.data)
            emit(Unit)
        } catch (e: Throwable) {
            error(e)
        }
    }
}