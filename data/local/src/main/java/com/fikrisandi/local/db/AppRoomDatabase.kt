package com.fikrisandi.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fikrisandi.local.converter.IntegerConverter
import com.fikrisandi.local.converter.TrailerConverter
import com.fikrisandi.local.dao.favorite.MovieFavoriteDao
import com.fikrisandi.model.local.MovieFavoriteEntity

@Database(
    version = 1,
    entities = [MovieFavoriteEntity::class],
    exportSchema = true,
)
@TypeConverters(
    TrailerConverter::class, IntegerConverter::class
)
abstract class AppRoomDatabase: RoomDatabase() {

    abstract fun getMovieFavoriteDao(): MovieFavoriteDao
}