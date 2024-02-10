package com.toadfrogson.clownhub.data.db

import com.toadfrogson.clownhub.data.model.JokeModel
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query

interface DatabaseAccessor {
    fun getCachedJokes(): List<JokeModel>

    suspend fun cacheJokes(jokes: List<JokeModel>?)
}

class RealmDatabaseAccessorImpl : DatabaseAccessor {
    private val config: RealmConfiguration =
        RealmConfiguration.create(schema = setOf(JokeModel::class))
    private val realm: Realm = Realm.open(config)


    override fun getCachedJokes(): List<JokeModel> {
        return realm.query<JokeModel>().find()
    }

    override suspend fun cacheJokes(jokes: List<JokeModel>?) {
        realm.writeBlocking {
            jokes?.forEach { copyToRealm(it) }
        }
    }
}