package com.geekbrains.popularlibraries.view.user.list

import com.geekbrains.popularlibraries.model.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserListView : MvpView {
    fun initList(list: List<GithubUser>)
    fun onUserClicked(user: GithubUser)
    fun showLoading()
    fun hideLoading()
}