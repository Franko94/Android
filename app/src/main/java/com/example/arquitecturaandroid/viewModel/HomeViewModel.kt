package com.example.arquitecturaandroid.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arquitecturaandroid.model.MarvelCharacter

class HomeViewModel: ViewModel() {
    private val characterRepository = CharacterRepository

    private val _characters = MutableLiveData<List<MarvelCharacter>>()
    val characters: LiveData<List<MarvelCharacter>>
        get() = _characters

    fun refreshCharacters(){
        viewModelScope.launch{
            characterRepository.fetchCharacters().run{
                _characters.postValue(this)
            }
        }
    }

}