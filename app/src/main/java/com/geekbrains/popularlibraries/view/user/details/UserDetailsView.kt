package com.geekbrains.popularlibraries.view.user.details

import com.geekbrains.popularlibraries.model.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserDetailsView : MvpView {
    fun initUser(user: GithubUser)
    fun toggleLoading(isVisible: Boolean)
}