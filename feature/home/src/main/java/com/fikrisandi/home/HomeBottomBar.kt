package com.fikrisandi.home

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
import com.ramcosta.composedestinations.utils.isRouteOnBackStack
import com.fikrisandi.provider.R

@Composable
fun HomeBottomBar(
    modifier: Modifier = Modifier,
    listScreen: List<BottomBarDestination>,
    selectedIndex: Int,
    onClick: (Int) -> Unit
) {
    NavigationBar(modifier = modifier.fillMaxWidth()) {
        listScreen.forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = listScreen[selectedIndex] == screen,
                onClick = { onClick.invoke(index) },
                icon = {
                    Icon(
                        painter = painterResource(screen.icon),
                        contentDescription = ""
                    )
                }, label = { Text(screen.label) })
        }
    }
}


sealed class BottomBarDestination(
    val label: String,
    @DrawableRes val icon: Int
) {

    data object GENRE : BottomBarDestination(
        label = "Movie", R.drawable.ic_movie
    )

    data object MOVIE : BottomBarDestination(
        label = "Genre", R.drawable.ic_category
    )
}