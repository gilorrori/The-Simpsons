package com.gilorroristore.thesimpsons.domain.repositories

import com.gilorroristore.thesimpsons.domain.model.CharacterDetailModel
import com.gilorroristore.thesimpsons.domain.model.CharactersModel


interface CharacterRepository {
    suspend fun getAllCharacters() : CharactersModel?

    suspend fun getIndividualCharacter(id: Int) : CharacterDetailModel?

    suspend fun loadNextPage(page: Int) : CharactersModel?
}