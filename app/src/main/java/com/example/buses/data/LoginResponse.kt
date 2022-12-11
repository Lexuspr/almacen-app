package com.example.buses.data

import com.example.buses.model.User

data class LoginResponse (
    val user: User,
    val token: String,
)