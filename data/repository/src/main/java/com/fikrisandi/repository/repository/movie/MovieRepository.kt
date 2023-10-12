package com.fikrisandi.repository.repository.movie

import com.fikrisandi.framework.network.DataState
import com.fikrisandi.model.remote.movie.Movie
import com.fikrisandi.model.remote.movie.Trailer
import com.fikrisandi.model.remote.user.UserReview

interface MovieRepository {
    suspend fun getAllByGenre(genre: Int, page: Int): DataState<List<Movie>>
    suspend fun getTrailerMovie(id: String): DataState<List<Trailer>>
    suspend fun getReview(id: String, page: Int): DataState<List<UserReview>>
}