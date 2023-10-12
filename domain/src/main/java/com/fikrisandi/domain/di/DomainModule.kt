package com.fikrisandi.domain.di

import com.fikrisandi.domain.favorite.movie.AddMovieFavorite
import com.fikrisandi.domain.favorite.movie.DeleteMovieFavorite
import com.fikrisandi.domain.favorite.movie.GetListMovieFavorite
import com.fikrisandi.domain.favorite.movie.GetMovieFavoriteById
import com.fikrisandi.domain.genre.GetListGenre
import com.fikrisandi.domain.movie.GetMovieByGenre
import com.fikrisandi.domain.movie.GetMovieReview
import com.fikrisandi.domain.movie.GetMovieTrailer
import com.fikrisandi.repository.repository.favorite.movie.MovieFavoriteRepository
import com.fikrisandi.repository.repository.genre.GenreRepository
import com.fikrisandi.repository.repository.movie.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideGetListGenre(repository: GenreRepository) = GetListGenre(repository)

    @Provides
    @Singleton
    fun provideGetMovieByGenre(repository: MovieRepository) = GetMovieByGenre(repository)

    @Provides
    @Singleton
    fun provideGetMovieReview(repository: MovieRepository) = GetMovieReview(repository)

    @Provides
    @Singleton
    fun provideGetMovieTrailer(repository: MovieRepository) = GetMovieTrailer(repository)

    @Provides
    @Singleton
    fun provideAddMovieFavorite(repository: MovieFavoriteRepository) = AddMovieFavorite(repository)

    @Provides
    @Singleton
    fun provideDeleteMovieFavorite(repository: MovieFavoriteRepository) =
        DeleteMovieFavorite(repository)

    @Provides
    @Singleton
    fun provideGetListMovieFavorite(repository: MovieFavoriteRepository) =
        GetListMovieFavorite(repository)


    @Provides
    @Singleton
    fun provideGetMovieFavoriteById(repository: MovieFavoriteRepository) =
        GetMovieFavoriteById(repository)

}