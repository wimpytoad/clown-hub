package com.toadfrogson.clownhub.app.di

import com.toadfrogson.clownhub.app.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val vmModule = module {
        viewModel { MainViewModel(get()) }
    }
}