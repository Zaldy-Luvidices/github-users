package com.test.githubusers.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.test.githubusers.R
import com.test.githubusers.adapter.UsersListAdapter
import com.test.githubusers.viewmodel.MainSharedViewModel
import com.test.githubusers.viewmodel.UsersViewModel

class UsersListFragment : Fragment(R.layout.fragment_users_list) {

    private val usersViewModel: UsersViewModel by viewModels()
    private val sharedViewModel: MainSharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = UsersListAdapter(emptyList()) { userLogin ->
            sharedViewModel.selectUser(userLogin)
            parentFragmentManager.beginTransaction()
                .add(R.id.fragmentMain, UserDetailsFragment())
                .hide(this)
                .addToBackStack(null)
                .commit()
        }
        val rvUsers: RecyclerView = view.findViewById(R.id.rvUsersList)
        rvUsers.layoutManager = LinearLayoutManager(view.context)
        rvUsers.adapter = adapter
        val pgbLoading: CircularProgressIndicator = view.findViewById(R.id.pgbLoading)
        val swipeRefresh: SwipeRefreshLayout = view.findViewById(R.id.swipeRefresh)

        swipeRefresh.setOnRefreshListener {
            usersViewModel.loadUsers()
        }

        usersViewModel.users.observe(viewLifecycleOwner) {
            pgbLoading.visibility = View.GONE
            swipeRefresh.isRefreshing = false
            adapter.updateItems(it)
        }
        usersViewModel.error.observe(viewLifecycleOwner) { message ->
            pgbLoading.visibility = View.GONE
            swipeRefresh.isRefreshing = false
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
        pgbLoading.visibility = View.VISIBLE
        usersViewModel.loadUsers()
    }
}