package com.example.movieapp.viewmodel.movie

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.model.genre.Genre
import com.example.movieapp.model.movie.Movie
import com.example.movieapp.usecase.genre.GetListGenre
import com.example.movieapp.usecase.movie.GetMovieByGenre
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MovieViewModel(private val getMovieByGenre: GetMovieByGenre, private val getListGenre: GetListGenre): ViewModel() {

    private val config = PagingConfig(pageSize = 10, initialLoadSize = 10, prefetchDistance = 10)

    private val _movieState: MutableStateFlow<PagingData<Movie>> = MutableStateFlow(PagingData.empty())
    val movieState = _movieState.asStateFlow()

    private val _genreState: MutableStateFlow<List<Genre>> = MutableStateFlow(emptyList())
    val genreState = _genreState.asStateFlow()

    private val loading = mutableStateOf(false)

    init{
        getListGenre()
    }

    suspend fun clearMovieState() {
        _movieState.emit(PagingData.empty())
    }

    fun getListGenre(){
        viewModelScope.launch {
            getListGenre(Unit).onStart {
                loading.value = true
            }.catch {e ->
                e.printStackTrace()
            }.collect{state ->
                _genreState.update { state.results ?: emptyList() }
                getListMovie(state.results?.firstOrNull()?.id ?: 0)
                loading.value = false
            }
        }
    }

    fun getListMovie(index: Int){
        viewModelScope.launch {
            getMovieByGenre(GetMovieByGenre.Params(
                config = config,
                option = mapOf(
                    "genre" to index
                )
            )).onStart {
                loading.value = true
            }.onCompletion {
                loading.value = false
            }.catch {e ->
                e.printStackTrace()
            }.cachedIn(viewModelScope).collect{
                _movieState.emit(it)
            }
        }
    }
}