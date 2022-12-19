package com.geekbrains.popularlibraries.view.converter

import com.geekbrains.popularlibraries.app.GeekBrainsApp
import com.geekbrains.popularlibraries.core.OnBackPressedListener
import com.geekbrains.popularlibraries.databinding.FragmentUserDetailsBinding
import com.geekbrains.popularlibraries.utils.ViewBindingFragment
import com.geekbrains.popularlibraries.view.user.details.UserDetailsPresenter
import moxy.ktx.moxyPresenter

class ConverterFragment :
    ViewBindingFragment<FragmentUserDetailsBinding>(FragmentUserDetailsBinding::inflate),
    ConverterView, OnBackPressedListener {

    private val presenter: ConverterPresenter by moxyPresenter {
        ConverterPresenter(GeekBrainsApp.instance.router)
    }

    override fun onBackPressed(): Boolean = presenter.onBackPressed()

    override fun initView() {
        TODO("Not yet implemented")
    }

}