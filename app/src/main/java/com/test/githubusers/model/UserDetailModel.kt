package com.test.githubusers.model

import com.google.gson.annotations.SerializedName

data class UserDetailModel (
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val bio: String?,
    @SerializedName("public_repos")
    val publicRepos: Int,
    val followers: Int,
    val following: Int,
)