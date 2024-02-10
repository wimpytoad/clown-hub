package com.toadfrogson.clownhub.data.di

import com.toadfrogson.clownhub.data.api.JokesApi
import com.toadfrogson.clownhub.data.api.JokesApiImpl
import com.toadfrogson.clownhub.data.db.DatabaseAccessor
import com.toadfrogson.clownhub.data.db.RealmDatabaseAccessorImpl
import com.toadfrogson.clownhub.data.network.WebClient
import com.toadfrogson.clownhub.data.repo.JokesRepo
import com.toadfrogson.clownhub.data.repo.JokesRepoImpl
import org.koin.dsl.module

object DataModule {
    val dataModule = module {
        single { WebClient() }
        factory<JokesRepo> { JokesRepoImpl(get(), get()) }
        factory<JokesApi> { JokesApiImpl(get()) }
        factory<DatabaseAccessor> { RealmDatabaseAccessorImpl() }
    }
}