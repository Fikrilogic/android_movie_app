package com.fikrisandi.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fikrisandi.model.remote.movie.Trailer


@Entity(
    tableName = MovieFavoriteEntity.DB_NAME
)
data class MovieFavoriteEntity(
    @PrimaryKey var id: Int = 0,
    var adult: Boolean? = null,
    var backdropPath: String? = null,
    var genreIds: List<Int?>? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var releaseDate: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null,
    var trailer: Trailer? = null
){
    companion object{
        const val DB_NAME = "movie_favorite"
    }
}