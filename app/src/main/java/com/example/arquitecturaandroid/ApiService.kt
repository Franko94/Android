package com.example.arquitecturaandroid

import com.example.arquitecturaandroid.model.CharacterDataWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("characters")
     fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        ): CharacterDataWrapper
}
