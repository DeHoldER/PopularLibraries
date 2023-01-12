package com.geekbrains.popularlibraries.main

import com.geekbrains.popularlibraries.core.nav.AppScreens
import com.geekbrains.popularlibraries.core.nav.AppScreensImpl
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var appScreens: AppScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
//        router.replaceScreen(AppScreensImpl)
        router.replaceScreen(appScreens.usersScreen())
    }

    fun onBackPressed() {
        router.exit()
    }
}