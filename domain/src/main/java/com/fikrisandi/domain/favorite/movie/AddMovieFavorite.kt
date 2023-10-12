package com.fikrisandi.domain.favorite.movie

import com.fikrisandi.framework.usecase.LocalUseCase
import com.fikrisandi.model.local.MovieFavoriteEntity
import com.fikrisandi.model.local.toEntity
import com.fikrisandi.model.remote.movie.Movie
import com.fikrisandi.model.remote.movie.Trailer
import com.fikrisandi.repository.repository.favorite.movie.MovieFavoriteRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class AddMovieFavorite @Inject constructor(private val repository: MovieFavoriteRepository) :
    LocalUseCase<AddMovieFavorite.Params, Unit>() {
    data class Params(
        val data: Movie,
        val trailer: Trailer
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        try {
            val entity = params.data.toEntity(trailer = params.trailer)
            repository.insert(entity)
            emit(Unit)
        } catch (e: Throwable) {
            error(e)
        }
    }
}