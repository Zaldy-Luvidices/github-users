package com.test.githubusers.repository

import com.test.githubusers.api.ApiClient
import com.test.githubusers.model.UserDetailModel
import com.test.githubusers.model.UserModel

class UsersRepository {
    suspend fun getUsers(): List<UserModel> {
        return ApiClient.api.getUsers()
    }
    suspend fun getUserDetails(login: String): UserDetailModel {
        return ApiClient.api.getUserDetails(login)
    }
}