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



    override fun getCachedJokes(): List<JokeModel> {
        return RealmConfig.realm.query<JokeModel>().find()
    }

    override suspend fun cacheJokes(jokes: List<JokeModel>?) {
        RealmConfig.realm.write {
            jokes?.forEach { copyToRealm(it) }
        }
    }
}

private object RealmConfig {
    private val config: RealmConfiguration =
        RealmConfiguration.create(schema = setOf(JokeModel::class))
    val realm: Realm = Realm.open(config)
}