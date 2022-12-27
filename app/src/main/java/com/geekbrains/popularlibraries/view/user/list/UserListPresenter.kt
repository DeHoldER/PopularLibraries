package com.geekbrains.popularlibraries.view.user.list

import com.geekbrains.popularlibraries.core.nav.AppScreens
import com.geekbrains.popularlibraries.model.GithubUser
import com.geekbrains.popularlibraries.repository.GithubRepository
import com.geekbrains.popularlibraries.utils.subscribeByDefault
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserListPresenter(
    private val repository: GithubRepository,
    private val router: Router
) : MvpPresenter<UserListView>() {

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
                { }
            )
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun onUserClicked(user: GithubUser) {
        router.navigateTo(
            AppScreens.userDetailsScreen(user.login)
        )
    }
}
