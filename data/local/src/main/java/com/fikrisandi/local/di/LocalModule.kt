package com.fikrisandi.local.di

import android.content.Context
import androidx.room.Room
import com.fikrisandi.local.db.AppRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppRoomDatabase::class.java, "database_movie").build()

    @Provides
    @Singleton
    fun provideMovieFavoriteDao(database: AppRoomDatabase) = database.getMovieFavoriteDao()
}