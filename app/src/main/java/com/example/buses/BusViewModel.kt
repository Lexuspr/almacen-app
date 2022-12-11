package com.example.buses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buses.data.Api
import com.example.buses.model.Bus
import com.example.buses.util.LocalStorage
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

}