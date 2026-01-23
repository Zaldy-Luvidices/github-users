package com.test.githubusers.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import coil.load
import com.test.githubusers.R
import com.test.githubusers.model.UserDetailModel
import com.test.githubusers.viewmodel.MainSharedViewModel
import com.test.githubusers.viewmodel.UsersViewModel

class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {

    private var imgAvatar: ImageView? = null
    private var txtName: TextView? = null

    private val usersViewModel: UsersViewModel by viewModels()
    private val sharedViewModel: MainSharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgAvatar = view.findViewById(R.id.imgAvatar)
        txtName = view.findViewById(R.id.txtName)
        usersViewModel.userDetails.observe(viewLifecycleOwner) {
            this.loadDetails(it)
        }
        sharedViewModel.selectedUserLogin.observe(viewLifecycleOwner) { userLogin ->
            usersViewModel.loadUserDetails(userLogin)
        }
    }

    private fun loadDetails(userDetails: UserDetailModel) {
        imgAvatar?.load(userDetails.avatarUrl)
        txtName?.text = userDetails.name
    }
}