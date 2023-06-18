package com.example.movieapp.framework.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.view.destinations.GenreScreenDestination
import com.example.movieapp.view.destinations.HomeScreenDestination
import com.example.movieapp.view.destinations.MovieDetailScreenDestination
import com.example.movieapp.view.destinations.MovieScreenDestination
import com.example.movieapp.view.genre.GenreScreen
import com.example.movieapp.view.home.HomeScreen
import com.example.movieapp.view.movie.MovieScreen
import com.example.movieapp.view.movie.detail.MovieDetailScreen
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.utils.composable

@Composable
fun AppNavHost(modifier: Modifier = Modifier,navController: NavHostController = rememberNavController(), startRoute: String, parentNavigation: DestinationsNavigator? = null){
    NavHost(navController = navController, startDestination = startRoute){
        composable(HomeScreenDestination){
            HomeScreen(modifier = modifier,navController = destinationsNavigator(navController))
        }

        composable(MovieDetailScreenDestination){
            MovieDetailScreen(modifier = modifier,navController = parentNavigation ?: destinationsNavigator(navController), detailParams = navArgs.detailParams)
        }

        composable(MovieScreenDestination){
            MovieScreen(modifier = modifier,navController = destinationsNavigator(navController), parentNavController = parentNavigation)
        }

        composable(GenreScreenDestination){
            GenreScreen(modifier = modifier,navController = destinationsNavigator(navController), parentNavController = parentNavigation)
        }
    }
}