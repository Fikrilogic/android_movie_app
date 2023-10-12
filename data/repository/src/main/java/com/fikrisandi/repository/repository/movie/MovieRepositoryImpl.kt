package com.fikrisandi.repository.repository.movie

import com.example.movieapp.common.constant.ConstantVal
import com.fikrisandi.framework.network.AppHttpClient
import com.fikrisandi.framework.network.DataState
import com.fikrisandi.model.remote.movie.Movie
import com.fikrisandi.model.remote.movie.Trailer
import com.fikrisandi.model.remote.user.UserReview
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(val appHttpClient: AppHttpClient) : MovieRepository {

    override suspend fun getAllByGenre(genre: Int, page: Int): DataState<List<Movie>> {
        return try {
            val result = appHttpClient().request("${ConstantVal.BASE_URL}/discover/movie") {

                method = HttpMethod.Get

                contentType(ContentType.Application.Json)

                url {
                    parameters.append("language", "en-US")
                    parameters.append("page", "$page")
                    parameters.append("with_genres","$genre" )
                }

            }

            return result.body()
        } catch (e: Throwable) {
            e.printStackTrace()
            DataState()
        }
    }

    override suspend fun getTrailerMovie(id: String): DataState<List<Trailer>> {
        return try {
            val result = appHttpClient().request("${ConstantVal.BASE_URL}/movie/${id}/videos") {

                method = HttpMethod.Get

                contentType(ContentType.Application.Json)

                url {
                    parameters.append("language", "en-US")
                }

            }

            return result.body()
        } catch (e: Throwable) {
            e.printStackTrace()
            DataState()
        }
    }

    override suspend fun getReview(id: String, page: Int): DataState<List<UserReview>> {
        return try {
            val result = appHttpClient().request("${ConstantVal.BASE_URL}/movie/${id}/reviews") {

                method = HttpMethod.Get

                contentType(ContentType.Application.Json)

                url {
                    parameters.append("language", "en-US")
                    parameters.append("page", "$page")
                }

            }

            return result.body()
        } catch (e: Throwable) {
            e.printStackTrace()
            DataState()
        }
    }

}