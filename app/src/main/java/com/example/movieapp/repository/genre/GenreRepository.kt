package com.example.movieapp.repository.genre

import com.example.movieapp.framework.network.DataState
import com.example.movieapp.model.genre.Genre


interface GenreRepository {

    suspend fun getMovieListGenre(): DataState<List<Genre>>
    suspend fun getTvListGenre():DataState<List<Genre>>
}