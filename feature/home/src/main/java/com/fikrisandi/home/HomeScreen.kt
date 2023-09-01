package com.fikrisandi.home


import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.fikrisandi.genre.list.GenreScreen
import com.fikrisandi.movie.list.MovieScreen
import com.fikrisandi.provider.EmptyNavigationProvider
import com.fikrisandi.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@Destination
@RootNavGraph(start = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigationProvider: NavigationProvider
) {
    var selectedIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val listScreen by remember {
        mutableStateOf(
            listOf(
                BottomBarDestination.MOVIE,
                BottomBarDestination.GENRE,
            )
        )
    }

    Crossfade(targetState = selectedIndex, label = "bottom_navigation") {index ->
        Scaffold(bottomBar = {
            HomeBottomBar(
                modifier = modifier.fillMaxWidth(),
                listScreen = listScreen,
                selectedIndex = selectedIndex,
                onClick = {
                    selectedIndex = it
                })

        }) {
            val modifier = Modifier.padding(it)
            when(listScreen[index]){
                BottomBarDestination.MOVIE -> {
                    MovieScreen(modifier = modifier,navigationProvider = navigationProvider, viewModel = hiltViewModel())
                }

                BottomBarDestination.GENRE -> {
                    GenreScreen(modifier = modifier,navigationProvider = navigationProvider, viewModel = hiltViewModel())
                }
            }
        }
    }
}