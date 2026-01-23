package com.test.githubusers.viewmodel

import android.util.Log
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

    fun loadUsers() {
        viewModelScope.launch {
            try {
                _users.value = repository.getUsers()
            } catch (e: Exception) {
                Log.e("", e.toString())
            }
        }
    }

    fun loadUserDetails(userLogin: String) {
        viewModelScope.launch {
            try {
                _userDetails.value = repository.getUserDetails(userLogin)
            } catch (e: Exception) {
                Log.e("", e.toString())
            }
        }
    }
}