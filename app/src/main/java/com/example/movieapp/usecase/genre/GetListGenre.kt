package com.example.movieapp.usecase.genre

import com.example.movieapp.framework.base.usecase.DataStateUseCase
import com.example.movieapp.framework.network.DataState
import com.example.movieapp.model.genre.Genre
import com.example.movieapp.repository.genre.GenreRepository
import kotlinx.coroutines.flow.FlowCollector
import org.koin.core.annotation.Single

@Single
class GetListGenre(val repository: GenreRepository): DataStateUseCase<Unit, List<Genre>>() {
    override suspend fun FlowCollector<DataState<List<Genre>>>.execute(params: Unit) {
        emit(repository.getMovieListGenre())
    }

}