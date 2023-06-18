package com.example.movieapp.viewmodel.movie.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.model.movie.Trailer
import com.example.movieapp.model.user.UserReview
import com.example.movieapp.usecase.movie.GetMovieReview
import com.example.movieapp.usecase.movie.GetMovieTrailer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class DetailMovieViewModel(
    private val getMovieTrailer: GetMovieTrailer,
    private val getMovieReview: GetMovieReview
) : ViewModel() {

    private val _listMovieTrailer: MutableStateFlow<List<Trailer>> = MutableStateFlow(emptyList())
    val listMovieTrailer = _listMovieTrailer.asStateFlow()
    private val _listReviewMovie: MutableStateFlow<PagingData<UserReview>> = MutableStateFlow(
        PagingData.empty()
    )
    val listReviewMovie = _listReviewMovie.asStateFlow()

    val loading = mutableStateOf(false)
    private val config = PagingConfig(pageSize = 5, initialLoadSize = 5, prefetchDistance = 5)

    fun getMovieTrailer(id: String) {
        viewModelScope.launch {
            getMovieTrailer(
                GetMovieTrailer.Params(id)
            ).onStart {
                loading.value = true
            }.catch { error ->
                error.printStackTrace()
            }.onCompletion {
                loading.value = false
            }.collect { dataState ->
                dataState.results?.let { list ->
                    _listMovieTrailer.update {
                        list
                    }
                }
            }
        }
    }

    fun getUserMovieReview(id: String) {
        viewModelScope.launch {
            getMovieReview(
                GetMovieReview.Params(
                    config = config,
                    option = mapOf(
                        "id" to id
                    )
                )
            ).onStart {
                loading.value = true
            }.catch { error ->
                error.printStackTrace()
            }.onCompletion {
                loading.value = false
            }.cachedIn(viewModelScope).collect {
                _listReviewMovie.emit(it)
            }
        }
    }

}