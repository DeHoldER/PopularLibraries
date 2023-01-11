package com.geekbrains.popularlibraries.view.user.details

import com.geekbrains.popularlibraries.model.GithubFork
import com.geekbrains.popularlibraries.model.GithubRepo
import com.geekbrains.popularlibraries.model.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserDetailsView : MvpView {
    fun initUser(user: GithubUser)
    fun initRepoList(list: List<GithubRepo>)
    fun toggleUserLoading(isVisible: Boolean)
    fun toggleReposLoading(isVisible: Boolean)
}