package com.test.githubusers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged

class MainSharedViewModel : ViewModel() {
    private val _selectedUserLogin = MutableLiveData<String>()
    val selectedUserLogin: LiveData<String> = _selectedUserLogin.distinctUntilChanged()

    fun selectUser(userLogin: String) {
        _selectedUserLogin.value = userLogin
    }
}