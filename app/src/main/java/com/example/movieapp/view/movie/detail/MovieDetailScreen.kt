package com.example.movieapp.view.movie.detail

import android.os.Parcelable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import com.example.movieapp.common.constant.ConstantVal
import com.example.movieapp.model.genre.Genre
import com.example.movieapp.model.movie.Movie
import com.example.movieapp.viewmodel.movie.detail.DetailMovieViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel


@Parcelize
data class MovieDetailParams(
    val movie: Movie?,
    val listGenre: List<Genre>
) : Parcelable

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    detailParams: MovieDetailParams,
    navController: DestinationsNavigator
) {
    val viewModel: DetailMovieViewModel = koinViewModel()
    var listStringGenre by remember { mutableStateOf<List<String>>(emptyList()) }
    val listTrailer = viewModel.listMovieTrailer.collectAsState()
    val listReview = viewModel.listReviewMovie.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.getMovieTrailer(detailParams.movie?.id?.toString() ?: "")
    }

    LaunchedEffect(Unit) {
        viewModel.getUserMovieReview(detailParams.movie?.id?.toString() ?: "")
    }

    LaunchedEffect(Unit) {
        listStringGenre = detailParams.listGenre.asFlow().filter { genre ->
            detailParams.movie?.genreIds?.contains(genre.id) == true
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
                    ) {
                        SubcomposeAsyncImage(
                            modifier = Modifier.fillMaxWidth(),
                            model = "${ConstantVal.IMAGE_BASE_URL}${detailParams.movie?.backdropPath}",
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            loading = {
                                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                            })
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
                            detailParams.movie?.title ?: "",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            detailParams.movie?.overview ?: "",
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

                items(listReview.itemCount) { index ->
                    val reviewData = listReview[index]

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(reviewData?.author ?: "", style = MaterialTheme.typography.titleSmall)
                        Text(
                            reviewData?.content ?: "",
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

            }
        }
    }
}