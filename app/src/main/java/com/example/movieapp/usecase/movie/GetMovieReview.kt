package com.example.movieapp.usecase.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.framework.base.usecase.FlowPagingUseCase
import com.example.movieapp.model.user.UserReview
import com.example.movieapp.repository.movie.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Single

@Single
class GetMovieReview(private val repository: MovieRepository): FlowPagingUseCase<GetMovieReview.Params, UserReview>() {
    data class Params(
        val config: PagingConfig,
        val option: Map<String, Any>
    )

    override fun execute(params: Params): Flow<PagingData<UserReview>> {
        return Pager(
            config = params.config,
            pagingSourceFactory ={MovieUserPagingSource(repository, params.option)}
        ).flow.flowOn(Dispatchers.IO)
    }

}