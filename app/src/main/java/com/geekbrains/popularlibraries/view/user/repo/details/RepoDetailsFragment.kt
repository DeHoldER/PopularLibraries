package com.geekbrains.popularlibraries.view.user.repo.details

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.popularlibraries.app.GeekBrainsApp
import com.geekbrains.popularlibraries.core.OnBackPressedListener
import com.geekbrains.popularlibraries.databinding.FragmentRepoDetailsBinding
import com.geekbrains.popularlibraries.model.GithubFork
import com.geekbrains.popularlibraries.model.GithubRepo
import com.geekbrains.popularlibraries.network.NetworkProvider
import com.geekbrains.popularlibraries.repository.impl.GithubRepositoryImpl
import com.geekbrains.popularlibraries.utils.ViewBindingFragment
import com.geekbrains.popularlibraries.utils.toggleVisibility
import moxy.ktx.moxyPresenter

class RepoDetailsFragment :
    ViewBindingFragment<FragmentRepoDetailsBinding>(FragmentRepoDetailsBinding::inflate),
    RepoDetailsView, OnBackPressedListener {

    companion object {
        const val ARG_LOGIN = "ARG_LOGIN"
        const val ARG_REPO_NAME = "ARG_REPO_NAME"
        fun newInstance(login: String, repoName: String): RepoDetailsFragment =
            RepoDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_LOGIN, login)
                    putString(ARG_REPO_NAME, repoName)
                }
            }
    }

    private val adapter = ForksListAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(fork: GithubFork) {
            presenter.onForkClicked(fork)
        }
    })

    private val presenter: RepoDetailsPresenter by moxyPresenter {
        RepoDetailsPresenter(
            GithubRepositoryImpl(
                NetworkProvider.usersApi,
                GeekBrainsApp.instance.database.userDao(),
                GeekBrainsApp.instance.getConnectSingle()
            )
        ).apply {
            GeekBrainsApp.instance.appComponent.inject(this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val login = arguments?.getString(ARG_LOGIN)
        val repoName = arguments?.getString(ARG_REPO_NAME)
        login?.let { repoName?.let { it1 -> presenter.loadRepo(it, it1) } }
        with(binding) {
            rvRepoForks.layoutManager = LinearLayoutManager(requireContext())
            rvRepoForks.adapter = adapter
        }
    }

    override fun initRepo(repo: GithubRepo) = with(binding) {
        txtRepoDetailsName.text = repo.name
    }

    override fun initForkList(list: List<GithubFork>) {
        adapter.forks = list
    }

    override fun toggleRepoLoading(isVisible: Boolean) {
        binding.repoProgressFrame.toggleVisibility(isVisible)
    }

    override fun toggleForksLoading(isVisible: Boolean) {
        binding.forksListProgressFrame.toggleVisibility(isVisible)
    }

    override fun onBackPressed() = presenter.onBackPressed()

    interface OnItemViewClickListener {
        fun onItemViewClick(fork: GithubFork)
    }
}