package com.example.movieapp.provider

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.fikrisandi.model.genre.Genre
import com.fikrisandi.model.movie.Movie
import com.fikrisandi.movie.detail.destinations.MovieDetailScreenDestination
import com.fikrisandi.provider.NavigationProvider
import com.ramcosta.composedestinations.navigation.navigate


class AppNavigationProvider(private val navController: NavController): NavigationProvider {
    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun navigateToListGenre(option: NavOptionsBuilder.() -> Unit) {}

    override fun navigateToListMovie(option: NavOptionsBuilder.() -> Unit) {}

    override fun navigateToDetailMovie(
        movie: Movie?,
        listGenre: List<Genre>,
        option: NavOptionsBuilder.() -> Unit
    ) {
        navController.navigate(MovieDetailScreenDestination.invoke(
            movie = movie,listGenre.toTypedArray()
        ))
    }


}