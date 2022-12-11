package com.example.buses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buses.data.Api
import com.example.buses.data.LoginRequest
import com.example.buses.data.RegisterRequest
import com.example.buses.util.LocalStorage
import com.example.buses.util.USER_ID
import com.example.buses.util.USER_TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val api: Api,
    private val localStorage: LocalStorage
): ViewModel() {

    val loading: MutableLiveData<Boolean> = MutableLiveData(false)

    val loginResult: MutableLiveData<Boolean?> = MutableLiveData(null)
    val registerResult: MutableLiveData<Boolean?> = MutableLiveData(null)

    fun login(email: String, password: String) {
        viewModelScope.launch {
            runCatching {
                loading.postValue(true)
                api.login(LoginRequest(email, password))
            }.onSuccess {
                loading.postValue(false)
                localStorage[USER_TOKEN] = it.token
                localStorage[USER_ID] = it.user.id
                loginResult.postValue(true)

            }.onFailure {
                loading.postValue(false)
                loginResult.postValue(false)
            }
        }
    }

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            runCatching {
                loading.postValue(true)
                api.register(RegisterRequest(name, email, password))
            }.onSuccess {
                loading.postValue(false)
                localStorage[USER_TOKEN] = it.token
                localStorage[USER_ID] = it.user.id
                registerResult.postValue(true)
            }.onFailure {
                loading.postValue(false)
                registerResult.postValue(false)
            }
        }
    }
}