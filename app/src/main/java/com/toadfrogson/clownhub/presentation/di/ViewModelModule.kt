package com.toadfrogson.clownhub.presentation.di

import com.toadfrogson.clownhub.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val vmModule = module {
        viewModel { MainViewModel(get()) }
    }
}