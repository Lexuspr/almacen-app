package com.example.buses.data

import com.example.buses.model.Bus

data class BusResponse (
    val total: Int,
    val buses: List<Bus>
)