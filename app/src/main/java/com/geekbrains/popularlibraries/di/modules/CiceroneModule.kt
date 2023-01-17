package com.geekbrains.popularlibraries.di.modules

import com.geekbrains.popularlibraries.core.nav.AppScreens
import com.geekbrains.popularlibraries.core.nav.AppScreensImpl
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
class CiceroneModule {
    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

    @Provides
    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    fun router(): Router = cicerone.router

    @Provides
    fun appScreens(): AppScreens {
        return AppScreensImpl()
    }
}