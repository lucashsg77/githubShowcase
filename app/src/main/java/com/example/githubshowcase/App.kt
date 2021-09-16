package com.example.githubshowcase

import android.app.Application
import com.example.githubshowcase.model.data.DataModule
import com.example.githubshowcase.model.data.DomainModule
import com.example.githubshowcase.viewmodel.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate(){
        super.onCreate()
        startKoin {
            androidContext(this@App)
        }
        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}