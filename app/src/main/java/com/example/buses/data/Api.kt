package com.example.buses.data

import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("users")
    suspend fun register(@Body request: RegisterRequest): LoginResponse
}