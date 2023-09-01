package com.fikrisandi.movie.detail

import android.os.Parcelable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import com.example.movieapp.common.constant.ConstantVal
import com.fikrisandi.model.genre.Genre
import com.fikrisandi.model.movie.Movie
import com.fikrisandi.provider.EmptyNavigationProvider
import com.fikrisandi.provider.NavigationProvider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieDetailParams(
    val movie: Movie?,
    val listGenre: List<Genre>
) : Parcelable

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@RootNavGraph(start = true)
@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    movie: Movie?,
    listGenre: Array<Genre> = emptyArray(),
    viewModel: DetailMovieViewModel = hiltViewModel(),
    navigationProvider: NavigationProvider
) {
    var listStringGenre by remember { mutableStateOf<List<String>>(emptyList()) }
    val listTrailer = viewModel.listMovieTrailer.collectAsState()
    val listReview = viewModel.listReviewMovie.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.getMovieTrailer(movie?.id?.toString() ?: "")
    }

    LaunchedEffect(Unit) {
        viewModel.getUserMovieReview(movie?.id?.toString() ?: "")
    }

    LaunchedEffect(Unit) {
        listStringGenre = listGenre.asFlow().filter { genre ->
            movie?.genreIds?.contains(genre.id) == true
        }.map { it.name ?: "" }.toList()
    }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = { Text("Movie", style = MaterialTheme.typography.titleLarge) })

    }) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (viewModel.loading.value) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .semantics(mergeDescendants = true) {}
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                userScrollEnabled = true,
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .padding(16.dp)
                            .border(border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondary), shape = MaterialTheme.shapes.medium),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        SubcomposeAsyncImage(
                            modifier = Modifier.fillMaxWidth().clip(shape = MaterialTheme.shapes.medium),
                            model = "${ConstantVal.IMAGE_BASE_URL}${movie?.backdropPath}",
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            error = {
                                Column(
                                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        "Something Error or Image Not Found",
                                        style = MaterialTheme.typography.headlineSmall
                                    )
                                }
                            },
                            loading = {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        )
                    }
                }
                item {
                    Column(
                        modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            movie?.title.orEmpty().ifEmpty { "-" },
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            movie?.overview.orEmpty().ifEmpty { "-" },
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Justify
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                "Genre: ", style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.Justify
                            )
                            if (listStringGenre.isNotEmpty()) {
                                Text(
                                    listStringGenre.joinToString(","),
                                    style = MaterialTheme.typography.bodySmall,
                                    textAlign = TextAlign.Justify
                                )
                            } else{
                                Text(
                                    "-",
                                    style = MaterialTheme.typography.bodySmall,
                                    textAlign = TextAlign.Justify
                                )
                            }
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text("Trailer", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        AndroidView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            factory = { context ->
                                YouTubePlayerView(context).apply {
                                    addYouTubePlayerListener(
                                        object : AbstractYouTubePlayerListener() {
                                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                                super.onReady(youTubePlayer)
                                                if (listTrailer.value.isNotEmpty()) {
                                                    youTubePlayer.loadVideo(
                                                        listTrailer.value.firstOrNull()?.key ?: "",
                                                        0f
                                                    )
                                                }
                                            }
                                        }
                                    )
                                }
                            })
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 16.dp),
                    ) {
                        Text("Review User", style = MaterialTheme.typography.titleMedium)
                    }
                }

                item {
                    when (listReview.itemCount < 1) {
                        true -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text("No Comment", style = MaterialTheme.typography.labelMedium)
                            }
                        }

                        else -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .padding(8.dp),
                                userScrollEnabled = true,
                                contentPadding = PaddingValues(vertical = 8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                items(listReview.itemCount) { index ->
                                    val reviewData = listReview[index]

                                    OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(8.dp),
                                            verticalArrangement = Arrangement.spacedBy(8.dp),
                                        ) {
                                            Text(
                                                reviewData?.author ?: "",
                                                style = MaterialTheme.typography.titleSmall
                                            )
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                                            ) {
                                                Text(
                                                    "-",
                                                    style = MaterialTheme.typography.bodySmall,
                                                    maxLines = 3,
                                                    overflow = TextOverflow.Ellipsis
                                                )
                                                Text(
                                                    reviewData?.content ?: "",
                                                    style = MaterialTheme.typography.bodySmall,
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }

            }
        }
    }
}