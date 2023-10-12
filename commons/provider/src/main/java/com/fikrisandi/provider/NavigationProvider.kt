package com.fikrisandi.provider

import androidx.navigation.NavOptionsBuilder
import com.fikrisandi.model.remote.genre.Genre
import com.fikrisandi.model.remote.movie.Movie

interface NavigationProvider {
    fun navigateBack()

    fun navigateToListGenre(option: NavOptionsBuilder.() -> Unit = {})
    fun navigateToListMovie(option: NavOptionsBuilder.() -> Unit = {})
    fun navigateToDetailMovie(movie: Movie?, listGenre: List<Genre>, option: NavOptionsBuilder.() -> Unit = {})

}

object EmptyNavigationProvider: NavigationProvider{
    override fun navigateBack() {
        TODO("Not yet implemented")
    }

    override fun navigateToListGenre(option: NavOptionsBuilder.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun navigateToListMovie(option: NavOptionsBuilder.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun navigateToDetailMovie(
        movie: Movie?,
        listGenre: List<Genre>,
        option: NavOptionsBuilder.() -> Unit
    ) {
        TODO("Not yet implemented")
    }


}