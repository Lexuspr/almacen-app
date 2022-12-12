package com.example.buses.data

import com.example.buses.model.Bus
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("users")
    suspend fun register(@Body request: RegisterRequest): LoginResponse

    @GET("buses")
    suspend fun getBuses(): BusResponse

    @POST("buses")
    suspend fun insertBus(@Body request: Bus): Bus
}