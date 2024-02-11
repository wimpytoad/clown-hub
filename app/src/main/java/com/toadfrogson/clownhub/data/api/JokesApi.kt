package com.toadfrogson.clownhub.data.api

import com.toadfrogson.clownhub.data.model.JokesEntity
import com.toadfrogson.clownhub.data.model.response.ApiResponse
import com.toadfrogson.clownhub.data.network.WebClient

interface JokesApi {
    suspend fun fetchJokes(): ApiResponse<JokesEntity>
}

class JokesApiImpl(private val client: WebClient) : JokesApi {
    override suspend fun fetchJokes(): ApiResponse<JokesEntity> =
        client.makeClientGet<JokesEntity>(DEFAULT_JOKE_FILTERS)

    companion object {
        private val DEFAULT_JOKE_FILTERS =
            "Programming,Miscellaneous,Pun,Spooky?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&type=single&amount=10"
    }
}