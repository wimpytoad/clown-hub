package com.toadfrogson.clownhub.data.repo

import com.toadfrogson.clownhub.data.model.JokeModel
import com.toadfrogson.clownhub.data.model.JokesEntity
import com.toadfrogson.clownhub.data.network.WebClient

interface JokesRepo {
    suspend fun fetchJokes(): Result<List<JokeModel>?>
}

class JokesRepoImpl(private val client: WebClient) : JokesRepo {
    override suspend fun fetchJokes(): Result<List<JokeModel>?> {
        val result = client.makeClientGet<JokesEntity>(DEFAULT_JOKE_FILTERS)
        return if (result.isSuccess && result.getOrNull()?.error == false) {
            Result.success(result.getOrNull()?.jokes?.map { JokeModel.mapFromEntity(it) })
        } else {
            Result.success(emptyList())
        }
    }

    companion object {
        private val DEFAULT_JOKE_FILTERS =
            "Programming,Miscellaneous,Spooky?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&type=single&amount=10"
    }
}

