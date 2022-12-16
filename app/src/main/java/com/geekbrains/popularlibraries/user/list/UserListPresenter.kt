package com.geekbrains.popularlibraries.user.list

import android.os.Bundle
import com.geekbrains.popularlibraries.core.nav.AppScreens
import com.geekbrains.popularlibraries.model.GithubUser
import com.geekbrains.popularlibraries.repository.GithubRepository
import com.geekbrains.popularlibraries.user.details.UserDetailsFragment
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserListPresenter(
    private val repository: GithubRepository,
    private val router: Router
) : MvpPresenter<UserListView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repository.getUsers().subscribe {
            viewState.initList(it)
            viewState.hideLoading()
        }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun onUserClicked(user: GithubUser) {
        router.navigateTo(
            AppScreens.userDetailsScreen(
                Bundle().apply {
                    putParcelable(
                        UserDetailsFragment.BUNDLE_EXTRA,
                        user
                    )
                })
        )
    }
}
