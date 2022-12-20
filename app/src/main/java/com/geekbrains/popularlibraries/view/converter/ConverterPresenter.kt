package com.geekbrains.popularlibraries.view.converter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter



class ConverterPresenter(
    private val router: Router
) : MvpPresenter<ConverterView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
//        viewState.initView()
    }

    fun onPickImageClicked() {
        viewState.pickImage()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun imagePicked(path: String) {

    }


}