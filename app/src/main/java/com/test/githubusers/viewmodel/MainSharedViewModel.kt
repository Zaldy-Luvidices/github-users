package com.test.githubusers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainSharedViewModel : ViewModel() {
    private val _selectedUserLogin = MutableLiveData<String>()
    val selectedUserLogin: LiveData<String> = _selectedUserLogin

    fun selectUser(userLogin: String) {
        _selectedUserLogin.value = userLogin
    }
}