package com.geekbrains.popularlibraries.view.user.repo.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.popularlibraries.R
import com.geekbrains.popularlibraries.model.GithubFork

class ForksListAdapter(
    private var onItemViewClickListener: RepoDetailsFragment.OnItemViewClickListener?
) : RecyclerView.Adapter<ForksListAdapter.ForkListViewHolder>() {

    var forks: List<GithubFork> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForkListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fork, parent, false)
        return ForkListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForkListViewHolder, position: Int) {
        holder.bind(forks[position])
    }

    override fun getItemCount(): Int = forks.size

    inner class ForkListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val forkName by lazy { itemView.findViewById<TextView>(R.id.tvForkName) }
        private val forkCard by lazy { itemView.findViewById<CardView>(R.id.itemForkListCard) }

        @SuppressLint("SetTextI18n")
        fun bind(item: GithubFork) = with(item) {
            forkName.text = "$id. $name"
            forkCard.setOnClickListener { onItemViewClickListener?.onItemViewClick(item) }
        }
    }
}