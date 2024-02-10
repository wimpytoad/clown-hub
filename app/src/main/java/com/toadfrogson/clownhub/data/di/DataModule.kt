package com.toadfrogson.clownhub.data.di

import com.toadfrogson.clownhub.data.network.WebClient
import com.toadfrogson.clownhub.data.repo.JokesRepo
import com.toadfrogson.clownhub.data.repo.JokesRepoImpl
import org.koin.dsl.module

object DataModule {
    val dataModule = module {
        single { WebClient() }
        factory<JokesRepo> { JokesRepoImpl(get()) }
    }
}