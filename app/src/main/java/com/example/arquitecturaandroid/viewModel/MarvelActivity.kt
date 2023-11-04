package com.example.arquitecturaandroid.viewModel

import android.os.Bundle
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.arquitecturaandroid.R
import com.example.arquitecturaandroid.model.CharacterDataWrapper
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.security.MessageDigest
import java.util.Date


class MarvelActivity : AppCompatActivity() {
    private lateinit var characterListTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel)

        characterListTextView = findViewById(R.id.characterListTextView)

        // Crea una instancia de la interfaz MarvelApi
        val marvelApi = MarvelApiClient().create()
        val timestamp = Date().time.toString()
        val publicKey = "270f253eea437575cc06ed3bc94e4746"
        val privateKey = "89f58fedefe2b9e86d96d0264af4f8ea312bf022"

        // Realiza la llamada a la API de Marvel
        val call =
            marvelApi?.getCharacters(publicKey, timestamp, "$timestamp$privateKey$publicKey".md5())
        call?.enqueue(object : Callback<CharacterDataWrapper> {
            override fun onResponse(
                call: Call<CharacterDataWrapper>,
                response: Response<CharacterDataWrapper>
            ) {
                if (response.isSuccessful) {
                    val characterResponse = response.body()
                    val characters = characterResponse?.data?.results

                    // Procesa la lista de personajes y muestra los resultados en el TextView
                    val characterNames = StringBuilder()
                    characters?.forEach { character ->
                        characterNames.append(character.name).append("\n")
                    }
                    characterListTextView.text = characterNames.toString()
                } else {
                    // Manejo de errores en caso de una respuesta no exitosa
                    Toast.makeText(
                        this@MarvelActivity,
                        "Error en la llamada a la API",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<CharacterDataWrapper>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun String.md5(): String {
        val bytes = MessageDigest.getInstance("MD5").digest(toByteArray())
        val sb = StringBuilder()
        for (byte in bytes) {
            sb.append(String.format("%02x", byte))
        }
        return sb.toString()
    }

}