package com.example.schoolapp.model

import com.example.schoolapp.interfaces.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://exercise.cngroup.dk/"
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    private fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = retrofitBuilder
            .client(UnsafeOkHttpClient().unsafeOkHttpClient)  // Use the custom OkHttpClient
            .build()
        return retrofit.create(serviceClass)
    }

    val apiService: ApiService = createService(ApiService::class.java)
}
