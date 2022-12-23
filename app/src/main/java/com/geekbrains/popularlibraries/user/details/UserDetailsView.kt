package com.geekbrains.popularlibraries.user.details

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserDetailsView : MvpView {
    fun initUser()
}