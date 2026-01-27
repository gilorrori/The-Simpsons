package com.gilorroristore.thesimpsons.domain.model

data class CharactersModel(
    val pages: Int,
    val results: List<CharacterDetailModel>,
    val next: String?
)

data class CharacterDetailModel(
    val id: Int,
    val age: Int,
    val birthdate: String,
    val gender: String,
    val name: String,
    val portraitPath: String,
    val phrases: List<String>,
    val status: String,
)