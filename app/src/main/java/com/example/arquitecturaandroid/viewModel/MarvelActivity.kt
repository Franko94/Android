package com.example.arquitecturaandroid.viewModel
import android.os.Bundle
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.arquitecturaandroid.ApiService
import com.example.arquitecturaandroid.R
import com.example.arquitecturaandroid.model.CharacterDataWrapper
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
class MarvelActivity : AppCompatActivity() {
    private lateinit var characterListTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel)

        characterListTextView = findViewById(R.id.characterListTextView)

        // Crea una instancia de la interfaz MarvelApi
        val marvelApi = MarvelClient.service

        // Realiza la llamada a la API de Marvel
        val call = marvelApi.getCharacters("TU_API_KEY", "TU_TIMESTAMP", "TU_HASH")

        call.enqueue(object : Callback<CharacterDataWrapper> {
            override fun onResponse(call: Call<CharacterDataWrapper>, response: Response<CharacterDataWrapper>) {
                if (response.isSuccessful) {
                    val characterResponse = response.body()
                    val characters = characterResponse?.data?.results

                    // Procesa la lista de personajes y muestra los resultados en el TextView
                    val characterNames = StringBuilder()
                    characters?.forEach { character ->
                        characterNames.append(character?.name).append("\n")
                    }
                    characterListTextView.text = characterNames.toString()
                } else {
                    // Manejo de errores en caso de una respuesta no exitosa
                    Toast.makeText(this@MarvelActivity, "Error en la llamada a la API", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                // Manejo de errores de la red
                Toast.makeText(this@MarvelActivity, "Error de red", Toast.LENGTH_SHORT).show()
            }
        })
    }
}