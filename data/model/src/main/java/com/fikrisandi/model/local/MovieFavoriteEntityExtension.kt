package com.fikrisandi.model.local

import com.fikrisandi.model.remote.movie.Movie
import com.fikrisandi.model.remote.movie.Trailer

fun Movie.toEntity(trailer: Trailer) = MovieFavoriteEntity(
    id = this.id ?: 0,
    adult = this.adult,
    backdropPath = this.backdropPath,
    genreIds = this.genreIds,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    title = this.title,
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
    trailer = trailer
)

fun MovieFavoriteEntity.toModel() = Movie(
    id = this.id,
    title = this.title,
    originalTitle = this.originalTitle,
    overview = this.overview,
    backdropPath = this.backdropPath,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    originalLanguage = this.originalLanguage,
    adult = this.adult,
    genreIds = emptyList(),
    voteCount = this.voteCount,
    voteAverage = this.voteAverage,
    video = this.video,
    popularity = this.popularity,
)