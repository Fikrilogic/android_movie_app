package com.fikrisandi.repository.repository.movie

import com.fikrisandi.framework.network.DataState
import com.fikrisandi.model.movie.Movie
import com.fikrisandi.model.movie.Trailer
import com.fikrisandi.model.user.UserReview

interface MovieRepository {
    suspend fun getAllByGenre(genre: Int, page: Int): DataState<List<Movie>>
    suspend fun getTrailerMovie(id: String): DataState<List<Trailer>>
    suspend fun getReview(id: String, page: Int): DataState<List<UserReview>>
}