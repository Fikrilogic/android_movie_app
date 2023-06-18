package com.example.movieapp.framework.network

import android.util.Log
import com.example.movieapp.common.constant.ConstantVal
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson
import org.koin.core.annotation.Single

@Single
class AppHttpClient {
    operator fun invoke() = HttpClient(Android){
        engine {
            connectTimeout = 60_000
        }

        install(Logging){
            logger = object: Logger{
                override fun log(message: String) {
                    Log.d("KtorHttpResponse", message)
                }

            }
        }

        install(ContentNegotiation){
            gson {
                setPrettyPrinting()
                setLenient()
                disableHtmlEscaping()
            }
        }

        install(Auth){

            bearer {

                loadTokens {
                    BearerTokens(accessToken = ConstantVal.API_ACCESS_TOKEN, refreshToken = "")
                }
            }
        }
    }
}