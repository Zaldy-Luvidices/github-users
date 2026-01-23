package com.test.githubusers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.githubusers.model.UserDetailModel
import com.test.githubusers.model.UserModel
import com.test.githubusers.repository.UsersRepository
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {
    private val repository = UsersRepository()

    private val _users = MutableLiveData<List<UserModel>>()
    val users: LiveData<List<UserModel>> = _users

    private val _userDetails = MutableLiveData<UserDetailModel>()
    val userDetails: LiveData<UserDetailModel> = _userDetails

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadUsers() {
        viewModelScope.launch {
            try {
                _users.value = repository.getUsers()
            } catch (e: retrofit2.HttpException) {
                _error.value = when (e.code()) {
                    401 -> "Unauthorized. Please check API token."
                    403 -> "Rate limit exceeded."
                    else -> "Server error (${e.code()})"
                }
            } catch (e: java.io.IOException) {
                _error.value = "Network error. Please check your internet."
            } catch (e: Exception) {
                _error.value = "Unexpected error occurred."
            }
        }
    }

    fun loadUserDetails(userLogin: String) {
        viewModelScope.launch {
            try {
                _userDetails.value = repository.getUserDetails(userLogin)
            } catch (e: retrofit2.HttpException) {
                _error.value = "Failed to load user details (${e.code()})"
            } catch (e: Exception) {
                _error.value = "Something went wrong."
            }
        }
    }
}