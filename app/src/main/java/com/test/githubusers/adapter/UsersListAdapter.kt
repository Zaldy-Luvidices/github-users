package com.test.githubusers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.githubusers.R
import com.test.githubusers.model.UserModel

class UsersListAdapter(
    private var users: List<UserModel>,
    private val onUserClick: (String) -> Unit
) : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val loginText: TextView = view.findViewById(R.id.txtUserLogin)
        val avatarImage: ImageView = view.findViewById(R.id.imgAvatar)
        val userTypeText: TextView? = view.findViewById(R.id.txtUserType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.loginText.text = user.login
        holder.avatarImage.load(user.avatarUrl);
        holder.userTypeText?.text = user.type
        holder.itemView.setOnClickListener {
            onUserClick(user.login)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun updateItems(newUsers: List<UserModel>) {
        val size = this.users.size
        this.users = emptyList()
        notifyItemRangeRemoved(0, size)
        this.users = newUsers
        notifyItemRangeInserted(0, this.users.size)
    }
}