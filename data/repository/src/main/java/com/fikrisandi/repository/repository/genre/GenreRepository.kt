package com.fikrisandi.repository.repository.genre

import com.fikrisandi.framework.network.DataState
import com.fikrisandi.model.genre.Genre


interface GenreRepository {

    suspend fun getMovieListGenre(): DataState<List<Genre>>
    suspend fun getTvListGenre():DataState<List<Genre>>
}