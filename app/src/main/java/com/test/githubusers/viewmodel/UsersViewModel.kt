package com.test.githubusers.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.githubusers.model.UserModel
import com.test.githubusers.repository.UsersRepository
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {
    private val repository = UsersRepository()

    private val _users = MutableLiveData<List<UserModel>>()
    val users: LiveData<List<UserModel>> = _users

    fun loadUsers() {
        viewModelScope.launch {
            try {
                _users.value = repository.getUsers()
            } catch (e: Exception) {
                Log.e("", e.toString())
            }
        }
    }
}