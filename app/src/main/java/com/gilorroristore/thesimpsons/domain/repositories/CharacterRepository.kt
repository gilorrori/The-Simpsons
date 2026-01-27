package com.gilorroristore.thesimpsons.domain.repositories

import com.gilorroristore.thesimpsons.domain.model.CharactersModel


interface CharacterRepository {
    suspend fun getAllCharacters() : CharactersModel?

    suspend fun loadNextPage(page: Int) : CharactersModel?
}