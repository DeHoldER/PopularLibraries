package com.geekbrains.popularlibraries.view.user.details

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.popularlibraries.app.GeekBrainsApp
import com.geekbrains.popularlibraries.core.OnBackPressedListener
import com.geekbrains.popularlibraries.databinding.FragmentUserDetailsBinding
import com.geekbrains.popularlibraries.model.GithubRepo
import com.geekbrains.popularlibraries.model.GithubUser
import com.geekbrains.popularlibraries.network.NetworkProvider
import com.geekbrains.popularlibraries.repository.impl.GithubRepositoryImpl
import com.geekbrains.popularlibraries.utils.ViewBindingFragment
import com.geekbrains.popularlibraries.utils.loadImage
import com.geekbrains.popularlibraries.utils.toggleVisibility
import com.geekbrains.popularlibraries.view.user.list.UserListAdapter
import com.geekbrains.popularlibraries.view.user.list.UserListFragment
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

    private val adapter = ReposListAdapter(object : UserDetailsFragment.OnItemViewClickListener {
        override fun onItemViewClick(repo: GithubRepo) {
            presenter.onRepoClicked(repo)
        }
    })

    private val presenter: UserDetailsPresenter by moxyPresenter {
        UserDetailsPresenter(
            GithubRepositoryImpl(NetworkProvider.usersApi),
            GeekBrainsApp.instance.router
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(ARG_LOGIN)?.let { presenter.loadUser(it) }
        with(binding) {
            rvUserRepos.layoutManager = LinearLayoutManager(requireContext())
            rvUserRepos.adapter = adapter
        }
    }

    override fun initUser(user: GithubUser) = with(binding) {
        txtUserDetailsName.text = user.login
        ivUserAvatar.loadImage(user.avatarUrl)
    }

    override fun initRepoList(list: List<GithubRepo>) {
        adapter.repos = list
    }

    override fun toggleUserLoading(isVisible: Boolean) {
        binding.userProgressFrame.toggleVisibility(isVisible)
    }

    override fun toggleReposLoading(isVisible: Boolean) {
        binding.repoListProgressFrame.toggleVisibility(isVisible)
    }


    override fun onBackPressed() = presenter.onBackPressed()

    interface OnItemViewClickListener {
        fun onItemViewClick(repo: GithubRepo)
    }
}