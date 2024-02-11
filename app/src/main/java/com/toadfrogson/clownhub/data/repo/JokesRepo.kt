package com.toadfrogson.clownhub.data.repo

import com.toadfrogson.clownhub.data.api.JokesApi
import com.toadfrogson.clownhub.data.db.DatabaseAccessor
import com.toadfrogson.clownhub.data.model.JokeModel
import com.toadfrogson.clownhub.data.model.exception.ClownException
import com.toadfrogson.clownhub.data.model.response.ApiResponse

interface JokesRepo {
    suspend fun fetchJokes(getFromCache: Boolean = false): ApiResponse<List<JokeModel>?>
}

class JokesRepoImpl(private val api: JokesApi, private val dbAccessor: DatabaseAccessor) :
    JokesRepo {
    override suspend fun fetchJokes(getFromCache: Boolean): ApiResponse<List<JokeModel>?> {
        if (getFromCache) {
            val cachedContent = dbAccessor.getCachedJokes()
            return if (cachedContent.isEmpty()) {
                ApiResponse.Failure(
                    "No content was found in cache",
                    ClownException.NoContentFoundInCache
                )
            } else {
                ApiResponse.Success(cachedContent)
            }

        }

        return when (val result = api.fetchJokes()) {
            is ApiResponse.Success -> {
                if (!result.data.error && result.data.jokes.isNotEmpty()) {
                    val data = result.data.jokes.map { JokeModel.mapFromEntity(it) }
                    dbAccessor.cacheJokes(data)
                    ApiResponse.Success(data)
                } else {
                    checkForContentInCache()
                }
            }
            //different exception handlers go here, for now just checking if data exist, if not return NoContentFound
            else -> {
                checkForContentInCache()
            }

        }

    }

    private fun checkForContentInCache(): ApiResponse<List<JokeModel>> {
        val cachedContent = dbAccessor.getCachedJokes()
        return if (cachedContent.isNotEmpty()) ApiResponse.Success(cachedContent)
        else ApiResponse.Failure("No content found in cache", ClownException.NoContentFoundInCache)
    }
}
