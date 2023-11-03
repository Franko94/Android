package com.example.arquitecturaandroid.viewModel

import com.example.arquitecturaandroid.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 class MarvelApiClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com:443/v1/public/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun create(): ApiService? {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}