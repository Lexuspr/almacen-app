package com.example.buses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buses.data.Api
import com.example.buses.model.Bus
import com.example.buses.util.LocalStorage
import com.example.buses.util.USER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusViewModel @Inject constructor(
    private val api: Api,
    private val localStorage: LocalStorage
): ViewModel() {
    val loading: MutableLiveData<Boolean> = MutableLiveData(false)

    val buses: MutableLiveData<List<Bus>> = MutableLiveData()
    val insertResult: MutableLiveData<Boolean?> = MutableLiveData(null)

    fun getBuses() {
        viewModelScope.launch {
            runCatching {
                loading.postValue(true)
                api.getBuses()
            }.onSuccess {
                loading.postValue(false)
                buses.postValue(it.buses)
            }.onFailure {
                loading.postValue(false)
            }
        }
    }

    fun addBus(placa: String) {
        viewModelScope.launch {
            runCatching {
                loading.postValue(true)
                api.insertBus(Bus(placa = placa, user_id = localStorage[USER_ID, 0L]!!))
            }.onSuccess {
                loading.postValue(false)
                insertResult.postValue(true)
            }.onFailure {
                val error = it
                loading.postValue(false)
                insertResult.postValue(false)
            }
        }
    }

}