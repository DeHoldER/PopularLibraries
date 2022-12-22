package com.geekbrains.popularlibraries.view.user.list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.popularlibraries.app.GeekBrainsApp
import com.geekbrains.popularlibraries.core.OnBackPressedListener
import com.geekbrains.popularlibraries.databinding.FragmentUserListBinding
import com.geekbrains.popularlibraries.model.GithubUser
import com.geekbrains.popularlibraries.network.NetworkProvider
import com.geekbrains.popularlibraries.repository.impl.GithubRepositoryImpl
import com.geekbrains.popularlibraries.utils.ViewBindingFragment
import moxy.ktx.moxyPresenter

class UserListFragment :
    ViewBindingFragment<FragmentUserListBinding>(FragmentUserListBinding::inflate),
    UserListView, OnBackPressedListener {

    companion object {
        fun getInstance(): UserListFragment {
            return UserListFragment()
        }
    }

    private val adapter = UserListAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(user: GithubUser) {
            onUserClicked(user)
        }
    })
    private val presenter: UserListPresenter by moxyPresenter {
        UserListPresenter(
            GithubRepositoryImpl(NetworkProvider.usersApi),
            GeekBrainsApp.instance.router
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvGithubUsers.layoutManager = LinearLayoutManager(requireContext())
            rvGithubUsers.adapter = adapter
        }
    }

    override fun initList(list: List<GithubUser>) {
        adapter.users = list
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(user: GithubUser)
    }

    override fun onUserClicked(user: GithubUser) {
        presenter.onUserClicked(user)
    }

    override fun showLoading() {
        binding.progressLayout.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressLayout.visibility = View.GONE
    }

    override fun onBackPressed() = presenter.onBackPressed()
}
