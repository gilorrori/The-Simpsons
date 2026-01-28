package com.gilorroristore.thesimpsons.ui.characters

import com.gilorroristore.thesimpsons.domain.model.CharacterDetailModel
import com.gilorroristore.thesimpsons.domain.model.CharactersModel

sealed class CharactersState() {

    data object None : CharactersState()
    data object Loading : CharactersState()
    data class Success(val charactersModel: CharactersModel, val characterDetailModel: List<CharacterDetailModel>) : CharactersState()
    data class SuccessNewList(val charactersModel: CharactersModel) : CharactersState()
    data class Error(val error: String) : CharactersState()
}