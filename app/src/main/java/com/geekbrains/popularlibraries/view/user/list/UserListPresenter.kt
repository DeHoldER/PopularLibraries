package com.geekbrains.popularlibraries.view.user.list

import com.geekbrains.popularlibraries.core.nav.AppScreens
import com.geekbrains.popularlibraries.core.nav.AppScreensImpl
import com.geekbrains.popularlibraries.model.GithubUser
import com.geekbrains.popularlibraries.repository.GithubRepository
import com.geekbrains.popularlibraries.utils.subscribeByDefault
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class UserListPresenter(private val repository: GithubRepository) : MvpPresenter<UserListView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var appScreens: AppScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.toggleLoading(true)
        repository.getUsers()
            .subscribeByDefault()
            .subscribe(
                {
                    viewState.initList(it)
                    viewState.toggleLoading(false)
                },
                {
                    //Error
                }
            )
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun onUserClicked(user: GithubUser) {
        router.navigateTo(appScreens.userDetailsScreen(user.login))
    }
}
