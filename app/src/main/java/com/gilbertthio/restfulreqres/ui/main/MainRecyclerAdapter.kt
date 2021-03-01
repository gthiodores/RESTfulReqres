package com.gilbertthio.restfulreqres.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gilbertthio.restfulreqres.databinding.ItemRecyclerUsersBinding
import com.gilbertthio.restfulreqres.network.Users

class MainRecyclerAdapter(private val clickListener: MainRecyclerClickListener) :
    ListAdapter<Users, MainRecyclerAdapter.UsersViewHolder>(UsersDiffUtilCallback()) {

    class UsersViewHolder(private val binding: ItemRecyclerUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): UsersViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemRecyclerUsersBinding.inflate(inflater, parent, false)
                return UsersViewHolder(binding)
            }
        }

        fun bind(user: Users, clickListener: MainRecyclerClickListener) {
            binding.user = user
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    private class UsersDiffUtilCallback : DiffUtil.ItemCallback<Users>() {
        override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem == newItem
        }
    }

    class MainRecyclerClickListener(private val clickListener: (Users) -> Unit) {
        fun onClick(user: Users) = clickListener(user)
    }
}