package com.fikrisandi.repository.repository.favorite.movie

import com.fikrisandi.model.local.MovieFavoriteEntity

interface MovieFavoriteRepository {

    fun get(id: Int): MovieFavoriteEntity
    fun get(limit:Int, offset: Int): List<MovieFavoriteEntity>
    fun insert(data: MovieFavoriteEntity)
    fun delete(data: MovieFavoriteEntity)
}