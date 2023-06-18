package com.example.movieapp.usecase.movie

import com.example.movieapp.framework.base.usecase.DataStateUseCase
import com.example.movieapp.framework.network.DataState
import com.example.movieapp.model.movie.Trailer
import com.example.movieapp.repository.movie.MovieRepository
import kotlinx.coroutines.flow.FlowCollector
import org.koin.core.annotation.Single

@Single
class GetMovieTrailer(private val repository: MovieRepository): DataStateUseCase<GetMovieTrailer.Params, List<Trailer>>() {
    data class Params(
        val id: String
    )

    override suspend fun FlowCollector<DataState<List<Trailer>>>.execute(params: Params) {
        emit(repository.getTrailerMovie(params.id))
    }
}