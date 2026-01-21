package com.test.githubusers.api

import com.test.githubusers.model.UserModel
import retrofit2.http.GET

interface GitHubApi {
    @GET("users")
    suspend fun getUsers(): List<UserModel>
}