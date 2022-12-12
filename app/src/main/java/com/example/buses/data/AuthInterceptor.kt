package com.example.buses.data

import android.util.Log
import com.example.buses.util.LocalStorage
import com.example.buses.util.USER_TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val localStorage: LocalStorage) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val token: String? = localStorage[USER_TOKEN]
        Log.d("HEADER", "X-TOKEN:  $token")
        if(token != null) {
            request = request.newBuilder()
                .addHeader("x-token", "$token")
                .build()
        }
        return chain.proceed(request)
    }
}