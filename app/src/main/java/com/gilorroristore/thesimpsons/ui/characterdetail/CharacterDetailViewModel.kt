package com.gilorroristore.thesimpsons.ui.characterdetail

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
class CharacterDetailViewModel @Inject constructor(private val repositoryImpl: CharacterRepositoryImpl) : ViewModel() {

    private var _state = MutableStateFlow<CharacterDetailState>(CharacterDetailState.Loading)
    val state: StateFlow<CharacterDetailState> = _state


    fun getDetailCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = CharacterDetailState.Loading
           val result = repositoryImpl.getIndividualCharacter(id)
            if (result != null) {
                _state.value = CharacterDetailState.Success(result)
            } else {
                _state.value = CharacterDetailState.Error("No se ha podido obtener el servicio")
            }
        }
    }
}