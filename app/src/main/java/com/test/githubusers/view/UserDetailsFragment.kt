package com.test.githubusers.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import coil.load
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.test.githubusers.R
import com.test.githubusers.model.UserDetailModel
import com.test.githubusers.viewmodel.MainSharedViewModel
import com.test.githubusers.viewmodel.UsersViewModel

class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {

    private lateinit var imgAvatar: ImageView
    private lateinit var txtName: TextView
    private lateinit var txtUsername: TextView
    private lateinit var txtCompany: TextView
    private lateinit var txtLocation: TextView
    private lateinit var txtBio: TextView
    private lateinit var txtRepos: TextView
    private lateinit var txtFollowers: TextView
    private lateinit var txtFollowing: TextView
    private lateinit var txtGithubLink: TextView
    private lateinit var txtBlog: TextView
    private lateinit var pgbLoading: CircularProgressIndicator

    private val usersViewModel: UsersViewModel by viewModels()
    private val sharedViewModel: MainSharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgAvatar = view.findViewById(R.id.imgAvatar)
        txtName = view.findViewById(R.id.txtName)
        txtUsername = view.findViewById(R.id.txtUsername)
        txtCompany = view.findViewById(R.id.txtCompany)
        txtLocation = view.findViewById(R.id.txtLocation)
        txtBio = view.findViewById(R.id.txtBio)
        txtRepos = view.findViewById(R.id.txtRepos)
        txtFollowers = view.findViewById(R.id.txtFollowers)
        txtFollowing = view.findViewById(R.id.txtFollowing)
        txtGithubLink = view.findViewById(R.id.txtGithubLink)
        txtBlog = view.findViewById(R.id.txtBlog)
        pgbLoading = view.findViewById(R.id.pgbLoading)

        usersViewModel.userDetails.observe(viewLifecycleOwner) {
            this.loadDetails(it)
        }
        usersViewModel.error.observe(viewLifecycleOwner) { message ->
            pgbLoading.visibility = View.GONE
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
        sharedViewModel.selectedUserLogin.observe(viewLifecycleOwner) { userLogin ->
            pgbLoading.visibility = View.VISIBLE
            usersViewModel.loadUserDetails(userLogin)
        }
    }

    private fun loadDetails(user: UserDetailModel) {
        imgAvatar.load(user.avatarUrl) {
            placeholder(R.drawable.ic_account_circle)
            error(R.drawable.ic_account_circle)
            crossfade(true)
        }
        txtName.text = user.name ?: user.login
        txtUsername.text = user.login
        txtCompany.isVisible = !user.company.isNullOrBlank()
        txtCompany.text = user.company
        txtLocation.isVisible = !user.location.isNullOrBlank()
        txtLocation.text = user.location
        txtBio.isVisible = !user.bio.isNullOrBlank()
        txtBio.text = user.bio
        txtRepos.text = getString(R.string.repos, user.publicRepos)
        txtFollowers.text = getString(R.string.followers, user.followers)
        txtFollowing.text = getString(R.string.following, user.following)
        txtGithubLink.text = getString(R.string.open_github_profile)
        txtGithubLink.setOnClickListener {
            openUrl(user.htmlUrl)
        }
        if (!user.blog.isNullOrBlank()) {
            txtBlog.isVisible = true
            txtBlog.text = user.blog
            txtBlog.setOnClickListener { openUrl(user.blog) }
        } else {
            txtBlog.isVisible = false
        }
        pgbLoading.visibility = View.GONE
    }

    private fun openUrl(url: String) {
        val fixedUrl = if (url.startsWith("http")) url else "https://$url"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(fixedUrl)))
    }
}