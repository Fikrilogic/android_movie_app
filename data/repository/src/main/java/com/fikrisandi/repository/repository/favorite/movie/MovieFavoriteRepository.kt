package com.fikrisandi.repository.repository.favorite.movie

import com.fikrisandi.model.local.MovieFavoriteEntity

interface MovieFavoriteRepository {

    suspend fun get(id: Int): MovieFavoriteEntity
    suspend fun get(limit:Int, offset: Int): List<MovieFavoriteEntity>
    suspend fun insert(data: MovieFavoriteEntity)
    suspend fun delete(data: MovieFavoriteEntity)
}