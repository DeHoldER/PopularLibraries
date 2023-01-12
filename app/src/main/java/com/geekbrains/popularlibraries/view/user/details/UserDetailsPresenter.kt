package com.geekbrains.popularlibraries.view.user.details

import com.geekbrains.popularlibraries.app.GeekBrainsApp
import com.geekbrains.popularlibraries.core.nav.AppScreens
import com.geekbrains.popularlibraries.core.nav.AppScreensImpl
import com.geekbrains.popularlibraries.model.GithubRepo
import com.geekbrains.popularlibraries.repository.GithubRepository
import com.geekbrains.popularlibraries.utils.disposeBy
import com.geekbrains.popularlibraries.utils.subscribeByDefault
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject

class UserDetailsPresenter(private val repository: GithubRepository,) : MvpPresenter<UserDetailsView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var appScreens: AppScreens

    private val bag = CompositeDisposable()

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun loadUser(login: String) {
        viewState.toggleUserLoading(true)
        repository.getUserByLogin(login)
            .subscribeByDefault()
            .subscribe(
                {
                    viewState.initUser(it)
                    viewState.toggleUserLoading(false)
                },
                {}
            ).disposeBy(bag)
        repository.getReposByLogin(login)
            .subscribeByDefault()
            .subscribe(
                {
                    viewState.initRepoList(it)
                    viewState.toggleReposLoading(false)
                },
                {}
            ).disposeBy(bag)
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }

    fun onRepoClicked(repo: GithubRepo) {
        router.navigateTo(appScreens.repoDetailsScreen(repo.ownerLogin, repo.name))
    }
}