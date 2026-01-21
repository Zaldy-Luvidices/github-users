package com.test.githubusers.api

import com.test.githubusers.model.UserDetailModel
import com.test.githubusers.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users")
    suspend fun getUsers(): List<UserModel>
    @GET("users/{login}")
    suspend fun getUserDetails(@Path("login") login: String): UserDetailModel
}