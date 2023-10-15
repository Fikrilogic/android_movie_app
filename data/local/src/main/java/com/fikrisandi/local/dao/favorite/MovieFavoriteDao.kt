package com.fikrisandi.local.dao.favorite

import androidx.room.Dao
import androidx.room.Query
import com.fikrisandi.framework.room.dao.BaseDao
import com.fikrisandi.model.local.MovieFavoriteEntity
import java.lang.IllegalStateException

@Dao
interface MovieFavoriteDao: BaseDao<MovieFavoriteEntity> {

    @Query("SELECT * FROM ${MovieFavoriteEntity.DB_NAME} WHERE id = :id")
    @Throws(IllegalStateException::class)
    suspend fun get(id: Int): MovieFavoriteEntity

    @Query("SELECT * FROM ${MovieFavoriteEntity.DB_NAME} LIMIT :limit OFFSET :offset")
//    @Throws(IllegalStateException::class)
    suspend fun get(limit: Int, offset: Int): List<MovieFavoriteEntity>
}