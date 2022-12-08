package com.geekbrains.popularlibraries.user.details

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserDetailsPresenter(
    private val router: Router
) : MvpPresenter<UserDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initUser()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}