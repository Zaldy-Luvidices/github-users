package com.test.githubusers.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.githubusers.R
import com.test.githubusers.adapter.UsersListAdapter
import com.test.githubusers.viewmodel.MainSharedViewModel
import com.test.githubusers.viewmodel.UsersViewModel

class UsersListFragment : Fragment(R.layout.fragment_users_list) {

    private val usersViewModel = UsersViewModel()
    private val sharedViewModel = MainSharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = UsersListAdapter(emptyList()) { userLogin ->
            sharedViewModel.selectUser(userLogin)

        }
        val rvUsers: RecyclerView = view.findViewById(R.id.rvUsersList)
        rvUsers.layoutManager = LinearLayoutManager(view.context)
        rvUsers.adapter = adapter

        usersViewModel.users.observe(viewLifecycleOwner) {
            adapter.updateItems(it)
        }
        usersViewModel.loadUsers()
    }
}