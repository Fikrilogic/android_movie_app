package com.fikrisandi.domain.genre


import com.fikrisandi.framework.network.DataState
import com.fikrisandi.framework.usecase.DataStateUseCase
import com.fikrisandi.model.genre.Genre
import com.fikrisandi.repository.repository.genre.GenreRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetListGenre @Inject constructor (private val repository: GenreRepository): DataStateUseCase<Unit, List<Genre>>() {
    override suspend fun FlowCollector<DataState<List<Genre>>>.execute(params: Unit) {
        emit(repository.getMovieListGenre())
    }

}