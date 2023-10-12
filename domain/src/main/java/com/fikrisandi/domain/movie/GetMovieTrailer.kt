package com.fikrisandi.domain.movie

import com.fikrisandi.framework.network.DataState
import com.fikrisandi.framework.usecase.DataStateUseCase
import com.fikrisandi.model.remote.movie.Trailer
import com.fikrisandi.repository.repository.movie.MovieRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetMovieTrailer @Inject constructor(private val repository: MovieRepository): DataStateUseCase<GetMovieTrailer.Params, List<Trailer>>() {
    data class Params(
        val id: String
    )

    override suspend fun FlowCollector<DataState<List<Trailer>>>.execute(params: Params) {
        emit(repository.getTrailerMovie(params.id))
    }
}