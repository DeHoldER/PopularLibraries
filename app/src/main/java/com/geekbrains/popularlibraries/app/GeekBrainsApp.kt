package com.geekbrains.popularlibraries.app

import android.app.Application
import com.geekbrains.popularlibraries.core.database.GithubAppDB
import com.geekbrains.popularlibraries.di.components.AppComponent
import com.geekbrains.popularlibraries.di.components.DaggerAppComponent
import com.geekbrains.popularlibraries.di.modules.ContextModule
import com.geekbrains.popularlibraries.network.AndroidNetworkStatus
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.internal.DaggerGenerated

class GeekBrainsApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    companion object {
        lateinit var instance: GeekBrainsApp
    }

    val database: GithubAppDB by lazy { GithubAppDB.create(this) }

    private lateinit var androidNetworkStatus: AndroidNetworkStatus

    override fun onCreate() {
        super.onCreate()
        instance = this

        androidNetworkStatus = AndroidNetworkStatus(this)
    }

    fun getConnectObservable() = androidNetworkStatus.isOnline()
    fun getConnectSingle() = androidNetworkStatus.isOnlineSingle()


}