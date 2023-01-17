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

    /*

    Евгений, большое спасибо за то что проявили участие в нашем отвратительно-организованном
    со стороны GB образовательном процессе.
    А то руки уже опустились совсем, ДЗ делать не было никакого желания, думаю это было заметно.
    И спасибо за более чем лояльные оценки.
    Хочется уже на самом деле поскорее, пока GB совсем не загнулся, дойти до курса по командной
    разработке, полчучить хоть какой-то опыт в этом и дальше уже самостоятельно закрывать пробелы в обучении.

    */
}