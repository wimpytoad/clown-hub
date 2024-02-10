package com.toadfrogson.clownhub.data.network

import com.toadfrogson.clownhub.data.model.response.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json


class WebClient {

    suspend inline fun <reified T: Any> makeClientGet(filters: String) : ApiResponse<T> {
        val url = BASE_URL + filters
        return try {
            val client = getWebClient()
            val response = client.get(url).body<T>()
            ApiResponse.Success(response)
        } catch (e: Exception) {
            ApiResponse.Failure(e.message.orEmpty(), e)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun getWebClient() = HttpClient(Android){
        engine {
            connectTimeout = 100_000
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
                explicitNulls = false
                ignoreUnknownKeys = true
            })
        }
    }

    companion object {
        const val BASE_URL = "https://v2.jokeapi.dev/joke/"
    }
}