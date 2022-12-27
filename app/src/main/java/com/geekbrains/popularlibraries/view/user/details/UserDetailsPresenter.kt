package com.geekbrains.popularlibraries.view.user.details

import com.geekbrains.popularlibraries.repository.GithubRepository
import com.geekbrains.popularlibraries.utils.disposeBy
import com.geekbrains.popularlibraries.utils.subscribeByDefault
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class UserDetailsPresenter(
    private val repository: GithubRepository,
    private val router: Router
) : MvpPresenter<UserDetailsView>() {

    private val bag = CompositeDisposable()

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun loadUser(login: String) {
        viewState.toggleLoading(true)
        repository.getUserByLogin(login)
            .subscribeByDefault()
            .subscribe(
                {
                    viewState.initUser(it)
                    viewState.toggleLoading(false)
                },
                {}
            ).disposeBy(bag)
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }
}