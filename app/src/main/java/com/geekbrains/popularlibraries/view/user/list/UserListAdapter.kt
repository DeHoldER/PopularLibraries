package com.geekbrains.popularlibraries.view.user.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.popularlibraries.R
import com.geekbrains.popularlibraries.model.GithubUser

class UserListAdapter(
    private var onItemViewClickListener: UserListFragment.OnItemViewClickListener?
) :
    RecyclerView.Adapter<UserListAdapter.GithubUserViewHolder>() {

    var users: List<GithubUser> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return GithubUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

    inner class GithubUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvLogin by lazy { itemView.findViewById<TextView>(R.id.tvUserLogin) }
        private val userCard by lazy { itemView.findViewById<CardView>(R.id.itemUserCardView) }

        fun bind(item: GithubUser) = with(item) {
            tvLogin.text = login
            userCard.setOnClickListener { onItemViewClickListener?.onItemViewClick(item) }
        }
    }
}
