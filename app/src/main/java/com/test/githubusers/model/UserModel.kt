package com.test.githubusers.model

import com.google.gson.annotations.SerializedName

data class UserModel (
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val type: String,
)