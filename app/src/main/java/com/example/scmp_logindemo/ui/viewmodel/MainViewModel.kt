package com.example.scmp_logindemo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scmp_logindemo.model.LoginResponse
import com.example.scmp_logindemo.network.Response
import com.example.scmp_logindemo.repository.ApiRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val apiRepository: ApiRepository by lazy { ApiRepository() }

    fun doLogin(email: String, pwd: String) {
        viewModelScope.launch {
            loginResult.value = apiRepository.doLogin(email, pwd)
        }
    }

    val loginResult: MutableLiveData<Response<LoginResponse>> by lazy {
        MutableLiveData<Response<LoginResponse>>()
    }
}