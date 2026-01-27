package com.gilorroristore.thesimpsons.ui.characterdetail

import com.gilorroristore.thesimpsons.domain.model.CharacterDetailModel
import com.gilorroristore.thesimpsons.domain.model.CharactersModel

sealed class CharacterDetailState() {
    data object Loading : CharacterDetailState()
    data class Success(val characterDetailModel: CharacterDetailModel) : CharacterDetailState()
    data class Error(val error: String) : CharacterDetailState()
}