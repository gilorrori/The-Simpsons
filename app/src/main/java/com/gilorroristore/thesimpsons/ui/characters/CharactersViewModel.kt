package com.gilorroristore.thesimpsons.ui.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilorroristore.thesimpsons.data.repositories.CharacterRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val characterRepositoryImpl: CharacterRepositoryImpl) : ViewModel() {
    private var _state = MutableStateFlow<CharactersState>(CharactersState.Loading)
    val state: StateFlow<CharactersState> = _state

    private var _newList = MutableStateFlow<CharactersState>(CharactersState.None)

    val newList: StateFlow<CharactersState> = _newList

    fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = characterRepositoryImpl.getAllCharacters()

            if (response != null) {
                _state.value =CharactersState.Success(response, response.results)
            } else {
                _state.value = CharactersState.Error("No se ha podido obtener el servicio")
            }
        }
    }

    fun loadNextPage(page: Int) {
        _newList.value = CharactersState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = characterRepositoryImpl.loadNextPage(page)

            if (response != null) {
                _newList.value = CharactersState.SuccessNewList(response)
                Log.d("MYLOG", "se consume bien")
            } else {
                _newList.value = CharactersState.Error("No se ha podido obtener el servicio")
                Log.d("MYLOG", "error al cargar nueva lista")
            }
        }
    }
}