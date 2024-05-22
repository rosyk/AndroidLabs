package com.example.lab10

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class CustomInterceptor: Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val logString = "Intercepted request headers: ${request.headers()}, URL: ${request.url()}"
        Log.d("Retrofit", logString)
        val response = chain.proceed(request)
        val logStringResponce = "Intercepted response headers: ${response.headers()}, body: ${response.body()}"
        Log.d("Retrofit", logStringResponce)
        return response
    }

}