package com.fikrisandi.repository.di

import com.fikrisandi.framework.network.AppHttpClient
import com.fikrisandi.local.dao.favorite.MovieFavoriteDao
import com.fikrisandi.repository.repository.favorite.movie.MovieFavoriteRepository
import com.fikrisandi.repository.repository.favorite.movie.MovieFavoriteRepositoryImpl
import com.fikrisandi.repository.repository.genre.GenreRepository
import com.fikrisandi.repository.repository.genre.GenreRepositoryImpl
import com.fikrisandi.repository.repository.movie.MovieRepository
import com.fikrisandi.repository.repository.movie.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGenreRepository(appClient: AppHttpClient): GenreRepository = GenreRepositoryImpl(appClient)

    @Provides
    @Singleton
    fun provideMovieRepository(appClient: AppHttpClient): MovieRepository = MovieRepositoryImpl(appClient)

    @Provides
    @Singleton
    fun provideMovieFavoriteRepository(dao: MovieFavoriteDao): MovieFavoriteRepository = MovieFavoriteRepositoryImpl(dao)
}