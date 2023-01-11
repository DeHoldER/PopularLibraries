package com.geekbrains.popularlibraries.view.user.repo.details

import com.geekbrains.popularlibraries.model.GithubFork
import com.geekbrains.popularlibraries.repository.GithubRepository
import com.geekbrains.popularlibraries.utils.disposeBy
import com.geekbrains.popularlibraries.utils.subscribeByDefault
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class RepoDetailsPresenter(
    private val repository: GithubRepository,
    private val router: Router
) : MvpPresenter<RepoDetailsView>() {

    private val bag = CompositeDisposable()

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun loadRepo(login: String, repoName: String) {
        viewState.toggleRepoLoading(true)
        viewState.toggleForksLoading(true)
        repository.getRepo(login, repoName)
            .subscribeByDefault()
            .subscribe(
                {
                    viewState.initRepo(it)
                    viewState.toggleRepoLoading(false)
                },
                {}
            ).disposeBy(bag)
        repository.getForks(login, repoName)
            .subscribeByDefault()
            .subscribe(
                {
                    viewState.initForkList(it)
                    viewState.toggleForksLoading(false)
                },
                {}
            ).disposeBy(bag)
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }

    fun onForkClicked(fork: GithubFork) {

    }
}