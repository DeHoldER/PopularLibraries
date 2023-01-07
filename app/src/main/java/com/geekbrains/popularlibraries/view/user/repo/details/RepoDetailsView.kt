package com.geekbrains.popularlibraries.view.user.repo.details

import com.geekbrains.popularlibraries.model.GithubFork
import com.geekbrains.popularlibraries.model.GithubRepo
import com.geekbrains.popularlibraries.model.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoDetailsView : MvpView {
    fun initRepo(repo: GithubRepo)
    fun initForkList(list: List<GithubFork>)
    fun toggleRepoLoading(isVisible: Boolean)
    fun toggleForksLoading(isVisible: Boolean)
}