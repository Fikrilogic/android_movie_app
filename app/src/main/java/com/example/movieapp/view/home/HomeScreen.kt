package com.example.movieapp.view.home


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.framework.navigation.AppNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@RootNavGraph(start = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: DestinationsNavigator
) {
    val localNavController = rememberNavController()
    Scaffold(bottomBar = {
        HomeBottomBar(modifier = modifier.fillMaxWidth(), navController = localNavController)

    }) {
//        DestinationsNavHost(
//            navGraph = NavGraphs.root,
//            startRoute = BottomBarDestination.values().first().direction,
//            navController = localNavController,
//            modifier = Modifier.padding(it)
//        )

        AppNavHost(modifier = Modifier.padding(it),navController = localNavController,startRoute = BottomBarDestination.values().first().direction.route, parentNavigation = navController)
    }

}