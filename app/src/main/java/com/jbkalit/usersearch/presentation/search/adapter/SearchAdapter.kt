package com.jbkalit.usersearch.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.jbkalit.domain.model.User
import com.jbkalit.usersearch.databinding.ViewUserItemBinding
import okhttp3.internal.userAgent

class SearchAdapter(private var userList: MutableList<User>)
    : ListAdapter<User, RecyclerView.ViewHolder>(UserResponseItemDiffCallback()) {

    override fun getItemCount() = userList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ViewUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(userList[position])
    }

    inner class ViewHolder(private val binding: ViewUserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                val url = GlideUrl(
                    user.avatarUrl, LazyHeaders.Builder()
                        .addHeader("User-Agent", userAgent)
                        .build()
                )
                Glide.with(this@ViewHolder.itemView)
                    .asBitmap()
                    .load(url)
                    .circleCrop()
                    .into(profilImageView)
                usernameTextView.text = user.login
            }
        }
    }

    class UserResponseItemDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    fun addToList(userList: MutableList<User>) {
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }

    fun clearList() {
        this.userList.clear()
        notifyDataSetChanged()
    }

}
