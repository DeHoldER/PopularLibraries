package com.geekbrains.popularlibraries.view.user.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.popularlibraries.R
import com.geekbrains.popularlibraries.model.GithubRepo

class ReposListAdapter(
    private var onItemViewClickListener: UserDetailsFragment.OnItemViewClickListener?
) : RecyclerView.Adapter<ReposListAdapter.RepoListViewHolder>() {

    var repos: List<GithubRepo> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    override fun getItemCount(): Int = repos.size

    inner class RepoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val repoName by lazy { itemView.findViewById<TextView>(R.id.tvRepoName) }
        private val repoCard by lazy { itemView.findViewById<CardView>(R.id.itemRepoListCard) }

        fun bind(item: GithubRepo) = with(item) {
            repoName.text = name
            repoCard.setOnClickListener { onItemViewClickListener?.onItemViewClick(item) }
        }
    }
}