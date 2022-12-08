package com.geekbrains.popularlibraries

class CountersPresenter(
    private val view: MainView
) {
    private val model = CountersModel()

    // Т.к. в реальной жизни, как правило, каждая кнопка имеет своё уникальное назначение,
    // нахожу целесообразным написать для каждой кнопки отдельные методы

    fun onBtnOneClicked() {
        view.btnOneSetText(model.next(0).toString())
    }

    fun onBtnTwoClicked() {
        view.btnTwoSetText(model.next(1).toString())
    }

    fun onBtnThreeClicked() {
        view.btnThreeSetText(model.next(2).toString())
    }

    fun onViewResumed() {
        view.btnOneSetText(model.getCurrent(0).toString())
        view.btnTwoSetText(model.getCurrent(1).toString())
        view.btnThreeSetText(model.getCurrent(2).toString())
    }

}