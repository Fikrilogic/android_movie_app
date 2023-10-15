package com.fikrisandi.repository.repository.favorite.movie

import android.util.Log
import com.fikrisandi.local.dao.favorite.MovieFavoriteDao
import com.fikrisandi.model.local.MovieFavoriteEntity
import javax.inject.Inject

class MovieFavoriteRepositoryImpl @Inject constructor(val dao: MovieFavoriteDao) :
    MovieFavoriteRepository {
    override suspend fun get(id: Int): MovieFavoriteEntity {
        return dao.get(id)
    }

    override suspend fun get(limit: Int, offset: Int): List<MovieFavoriteEntity> {
        return try {
            dao.get(limit, offset)
        } catch (e: Throwable) {
            e.printStackTrace()
            emptyList()
        }
    }


    override suspend fun insert(data: MovieFavoriteEntity) {
        dao.insert(data)
    }

    override suspend fun delete(data: MovieFavoriteEntity) {
        dao.delete(data)
    }
}