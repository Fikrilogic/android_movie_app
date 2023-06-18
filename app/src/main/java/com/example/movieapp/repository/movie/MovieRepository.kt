package com.example.movieapp.repository.movie

import com.example.movieapp.framework.network.DataState
import com.example.movieapp.model.movie.Movie
import com.example.movieapp.model.movie.Trailer
import com.example.movieapp.model.user.UserReview

interface MovieRepository {
    suspend fun getAllByGenre(genre: Int, page: Int): DataState<List<Movie>>
    suspend fun getTrailerMovie(id: String): DataState<List<Trailer>>
    suspend fun getReview(id: String, page: Int): DataState<List<UserReview>>
}