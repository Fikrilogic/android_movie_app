package com.example.movieapp.view.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.view.destinations.GenreScreenDestination
import com.example.movieapp.view.destinations.HomeScreenDestination
import com.example.movieapp.view.destinations.MovieScreenDestination
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popBackStack
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.ramcosta.composedestinations.utils.isRouteOnBackStack

@Composable
fun HomeBottomBar(modifier: Modifier = Modifier, navController: NavController) {
    NavigationBar(modifier = modifier.fillMaxWidth()) {
        BottomBarDestination.values().forEach { bottomBarDestination ->
            val isCurrentDestOnBackStack =
                navController.isRouteOnBackStack(bottomBarDestination.direction)
            NavigationBarItem(selected = isCurrentDestOnBackStack, onClick = {
                if (isCurrentDestOnBackStack) {
                    navController.popBackStack(bottomBarDestination.direction, false)
                    return@NavigationBarItem
                }

                navController.navigate(bottomBarDestination.direction) {
                    popUpTo(HomeScreenDestination.route) {
                        saveState = true
                    }

                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            }, icon = {
                Icon(
                    painter = painterResource(bottomBarDestination.icon),
                    contentDescription = ""
                )
            }, label = { Text(bottomBarDestination.label) })
        }
    }
}

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val label: String,
    @DrawableRes val icon: Int
) {
    MovieScreenBottomBar(MovieScreenDestination, "Movie", R.drawable.ic_movie),
    GenreScreenBottomBar(GenreScreenDestination, "Genre", R.drawable.ic_category)
}