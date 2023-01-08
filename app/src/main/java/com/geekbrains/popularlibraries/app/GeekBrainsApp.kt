package com.geekbrains.popularlibraries.app

import android.app.Application
import android.net.ConnectivityManager
import com.bumptech.glide.manager.ConnectivityMonitor
import com.geekbrains.popularlibraries.core.database.GithubAppDB
import com.geekbrains.popularlibraries.network.AndroidNetworkStatus
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class GeekBrainsApp : Application() {

    companion object {
        lateinit var instance: GeekBrainsApp
    }

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

    val navigationHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router

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