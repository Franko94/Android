package com.example.arquitecturaandroid.viewModel

import com.example.arquitecturaandroid.model.MarvelCharacter
import java.util.Date

object CharacterRepository {
    suspend fun fetchCharacters(): List<MarvelCharacter> {
        val timestamp = Date().time.toString()
        val characters = MarvelClient().service
            .listCharacters(
                apiKey = "270f253eea437575cc06ed3bc94e4746",
                orderBy = "-modified",
                ts = timestamp,
                hash = "${timestamp}89f58fedefe2b9e86d96d0264af4f8ea312bf022270f253eea437575cc06ed3bc94e4746"

            )
        return CharacterNetworkMapper
    }
}