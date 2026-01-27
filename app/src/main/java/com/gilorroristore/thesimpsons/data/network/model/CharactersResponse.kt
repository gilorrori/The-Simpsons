package com.gilorroristore.thesimpsons.data.network.model

import com.gilorroristore.thesimpsons.domain.model.CharacterDetailModel
import com.gilorroristore.thesimpsons.domain.model.CharactersModel
import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("pages") val pages: Int?,
    @SerializedName("results") val results: List<CharacterDetailResponse>?,
    @SerializedName("next") val next: String?
)

fun CharactersResponse.toDomain(): CharactersModel {
    return CharactersModel(
        pages = pages ?: 0,
        results = results?.map { it.toDomain() } ?: emptyList(),
        next = next ?: ""
    )
}

data class CharacterDetailResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("age") val age: Int?,
    @SerializedName("birthdate") val birthdate: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("portrait_path") val portraitPath: String?,
    @SerializedName("phrases") val phrases: List<String>?,
    @SerializedName("status") val status: String?,
)

fun CharacterDetailResponse.toDomain(): CharacterDetailModel {
    return CharacterDetailModel(
        id = id ?: 0,
        age = age ?: 0,
        birthdate = birthdate ?: "",
        gender = gender ?: "",
        name = name ?: "",
        portraitPath = portraitPath ?: "",
        phrases = phrases ?: emptyList(),
        status = status ?: ""
    )
}