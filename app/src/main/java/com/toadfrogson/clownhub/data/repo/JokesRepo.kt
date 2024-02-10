package com.toadfrogson.clownhub.data.repo

import com.toadfrogson.clownhub.data.model.JokesEntity
import com.toadfrogson.clownhub.data.network.WebClient

interface JokesRepo {
    suspend fun fetchJokes() : Result<JokesEntity>
}

class JokesRepoImpl(private val client: WebClient) : JokesRepo {
    override suspend fun fetchJokes(): Result<JokesEntity> {
        return client.makeClientGet<JokesEntity>(DEFAULT_JOKE_FILTERS)
    }

    companion object {
        private val DEFAULT_JOKE_FILTERS = "Programming,Miscellaneous,Spooky?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&type=single&amount=10"
    }
}

