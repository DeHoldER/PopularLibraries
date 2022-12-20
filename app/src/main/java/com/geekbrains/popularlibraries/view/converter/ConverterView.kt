package com.geekbrains.popularlibraries.view.converter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ConverterView : MvpView {
    fun pickImage()
}
