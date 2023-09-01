package com.fikrisandi.repository.repository.genre

import com.example.movieapp.common.constant.ConstantVal
import com.fikrisandi.framework.network.AppHttpClient
import com.fikrisandi.framework.network.DataState
import com.fikrisandi.model.genre.Genre
import com.fikrisandi.model.genre.Genres
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import javax.inject.Inject


class GenreRepositoryImpl @Inject constructor (val appHttpClient: AppHttpClient): GenreRepository {
    override suspend fun getMovieListGenre(): DataState<List<Genre>> {
        return try {
            val result = appHttpClient().request("${ConstantVal.BASE_URL}/genre/movie/list") {

                method = HttpMethod.Get

                contentType(ContentType.Application.Json)

                url {
                    parameters.append("language", "en-US")
                }

            }
            val data = result.body<Genres>()
            println("$data")
            return DataState(results = data.item)
        } catch (e: Throwable) {
            e.printStackTrace()
            DataState()
        }
    }

    override suspend fun getTvListGenre(): DataState<List<Genre>> {
        return try {
            val result = appHttpClient().request("${ConstantVal.BASE_URL}/genre/tv/list") {

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
}