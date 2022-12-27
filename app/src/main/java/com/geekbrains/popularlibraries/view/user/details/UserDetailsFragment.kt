package com.geekbrains.popularlibraries.view.user.details

import android.os.Bundle
import android.view.View
import com.geekbrains.popularlibraries.app.GeekBrainsApp
import com.geekbrains.popularlibraries.core.OnBackPressedListener
import com.geekbrains.popularlibraries.databinding.FragmentUserDetailsBinding
import com.geekbrains.popularlibraries.model.GithubUser
import com.geekbrains.popularlibraries.network.NetworkProvider
import com.geekbrains.popularlibraries.repository.impl.GithubRepositoryImpl
import com.geekbrains.popularlibraries.utils.ViewBindingFragment
import com.geekbrains.popularlibraries.utils.loadImage
import com.geekbrains.popularlibraries.utils.toggleVisibility
import moxy.ktx.moxyPresenter

class UserDetailsFragment :
    ViewBindingFragment<FragmentUserDetailsBinding>(FragmentUserDetailsBinding::inflate),
    UserDetailsView, OnBackPressedListener {

    companion object {
        const val ARG_LOGIN = "ARG_LOGIN"
        fun newInstance(login: String): UserDetailsFragment =
            UserDetailsFragment().apply {
                arguments = Bundle().apply { putString(ARG_LOGIN, login) }
            }
    }

    private val presenter: UserDetailsPresenter by moxyPresenter {
        UserDetailsPresenter(
            GithubRepositoryImpl(NetworkProvider.usersApi),
            GeekBrainsApp.instance.router
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(ARG_LOGIN)?.let { presenter.loadUser(it) }
    }

    override fun initUser(user: GithubUser) = with(binding) {
        txtUserDetailsName.text = user.login
        ivUserAvatar.loadImage(user.avatarUrl)
    }

    override fun toggleLoading(isVisible: Boolean) {
        binding.progressLayout.toggleVisibility(isVisible)
    }

    override fun onBackPressed() = presenter.onBackPressed()
}