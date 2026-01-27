package com.gilorroristore.thesimpsons.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilorroristore.thesimpsons.data.repositories.CharacterRepositoryImpl
import com.gilorroristore.thesimpsons.domain.model.CharacterDetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val characterRepositoryImpl: CharacterRepositoryImpl) :
    ViewModel() {

    private var listCharacterDetailModel : List<CharacterDetailModel> = emptyList()
    private var _state = MutableStateFlow<CharactersState>(CharactersState.Loading)
    val state: StateFlow<CharactersState> = _state

    private var _newList = MutableStateFlow<CharactersState>(CharactersState.Loading)

    val newList: StateFlow<CharactersState> = _newList

    fun getAllCharacters() {
        _state.value = CharactersState.Loading

        viewModelScope.launch {
            val response = characterRepositoryImpl.getAllCharacters()

            if (response != null) {
                listCharacterDetailModel = response.results
                _state.value = CharactersState.Success(response, listCharacterDetailModel)
            } else {
                _state.value = CharactersState.Error("No se ha podido obtener el servicio")
            }
        }
    }

    fun loadNextPage(page: Int) {
        _newList.value = CharactersState.Loading
        viewModelScope.launch {
            val response = characterRepositoryImpl.loadNextPage(page)

            if (response != null) {
                _newList.value = CharactersState.Success(response,  response.results)
            } else {
                _newList.value = CharactersState.Error("No se ha podido obtener el servicio")
            }
        }
    }

}