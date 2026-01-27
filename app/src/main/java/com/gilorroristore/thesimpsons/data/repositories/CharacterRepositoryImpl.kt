package com.gilorroristore.thesimpsons.data.repositories

import com.gilorroristore.thesimpsons.data.network.SimpsonsApiService
import com.gilorroristore.thesimpsons.data.network.model.toDomain
import com.gilorroristore.thesimpsons.domain.model.CharactersModel
import com.gilorroristore.thesimpsons.domain.repositories.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val simpsonsApiService: SimpsonsApiService): CharacterRepository {

    override suspend fun getAllCharacters(): CharactersModel? {
        runCatching {
            simpsonsApiService.getAllCharacters()
        }.onSuccess {
            return it.toDomain()
        }.onFailure {
            return null
            // Retornar error
        }
        return null
    }

    override suspend fun loadNextPage(page: Int): CharactersModel? {


        runCatching {
            simpsonsApiService.loadNextPage(page)
        }.onSuccess {
            return it.toDomain()
        }.onFailure {
            return null
            // Retornar error
        }
        return null
    }
}