package com.fikrisandi.domain.favorite.movie

import com.fikrisandi.framework.usecase.LocalUseCase
import com.fikrisandi.model.local.MovieFavoriteEntity
import com.fikrisandi.repository.repository.favorite.movie.MovieFavoriteRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetMovieFavoriteById @Inject constructor(private val repository: MovieFavoriteRepository) :
    LocalUseCase<GetMovieFavoriteById.Params, MovieFavoriteEntity?>() {
    data class Params(
        val id: Int
    )

    override suspend fun FlowCollector<MovieFavoriteEntity?>.execute(params: Params) {
        try {
            val data = repository.get(params.id)
            emit(data)
        } catch (e: Exception) {
            emit(null)
        }
    }
}