package com.geekbrains.popularlibraries.view.user.details

import android.os.Bundle
import android.view.View
import com.geekbrains.popularlibraries.app.GeekBrainsApp
import com.geekbrains.popularlibraries.core.OnBackPressedListener
import com.geekbrains.popularlibraries.databinding.FragmentUserDetailsBinding
import com.geekbrains.popularlibraries.model.GithubUser
import com.geekbrains.popularlibraries.utils.ViewBindingFragment
import moxy.ktx.moxyPresenter

class UserDetailsFragment :
    ViewBindingFragment<FragmentUserDetailsBinding>(FragmentUserDetailsBinding::inflate),
    UserDetailsView, OnBackPressedListener {

    private lateinit var userBundle: GithubUser

    companion object {
        const val BUNDLE_EXTRA = "BUNDLE_EXTRA"
        fun newInstance(bundle: Bundle): UserDetailsFragment =
            UserDetailsFragment().apply { arguments = bundle }
    }

    private val presenter: UserDetailsPresenter by moxyPresenter {
        UserDetailsPresenter(GeekBrainsApp.instance.router)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let {
            it?.getParcelable<GithubUser>(BUNDLE_EXTRA) ?: GithubUser(
                id = 0,
                login = "error",
                ""
            )
        }
            .also { userBundle = it }
    }

    override fun initUser() = with(binding) {
        txtUserDetailsName.text = userBundle.login
    }

    override fun onBackPressed() = presenter.onBackPressed()
}