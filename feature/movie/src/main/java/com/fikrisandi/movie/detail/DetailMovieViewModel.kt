package com.fikrisandi.movie.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fikrisandi.domain.movie.GetMovieReview
import com.fikrisandi.domain.movie.GetMovieTrailer
import com.fikrisandi.model.movie.Trailer
import com.fikrisandi.model.user.UserReview
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor (
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