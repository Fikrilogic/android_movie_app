package com.fikrisandi.repository.repository.favorite.movie

import com.fikrisandi.local.dao.favorite.MovieFavoriteDao
import com.fikrisandi.model.local.MovieFavoriteEntity
import javax.inject.Inject

class MovieFavoriteRepositoryImpl @Inject constructor(val dao: MovieFavoriteDao) :
    MovieFavoriteRepository {
    override fun get(id: Int): MovieFavoriteEntity {
        return dao.get(id)
    }

    override fun get(limit: Int, offset: Int): List<MovieFavoriteEntity> {
        return dao.get(limit, offset)
    }


    override fun insert(data: MovieFavoriteEntity) {
        dao.insert(data)
    }

    override fun delete(data: MovieFavoriteEntity) {
        dao.delete(data)
    }
}