package com.fikrisandi.domain.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fikrisandi.framework.usecase.FlowPagingUseCase
import com.fikrisandi.model.remote.user.UserReview
import com.fikrisandi.repository.repository.movie.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class GetMovieReview @Inject constructor(private val repository: MovieRepository): FlowPagingUseCase<GetMovieReview.Params, UserReview>() {
    data class Params(
        val config: PagingConfig,
        val option: Map<String, Any>
    )

    override fun execute(params: Params): Flow<PagingData<UserReview>> {
        return Pager(
            config = params.config,
            pagingSourceFactory ={ MovieUserPagingSource(repository, params.option) }
        ).flow.flowOn(Dispatchers.IO)
    }

}