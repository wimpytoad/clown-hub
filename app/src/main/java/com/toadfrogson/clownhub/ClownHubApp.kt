package com.toadfrogson.clownhub

import android.app.Application
import com.toadfrogson.clownhub.app.di.ViewModelModule
import com.toadfrogson.clownhub.data.di.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ClownHubApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ClownHubApp)
            modules(ViewModelModule.vmModule, DataModule.dataModule)
        }
    }
}