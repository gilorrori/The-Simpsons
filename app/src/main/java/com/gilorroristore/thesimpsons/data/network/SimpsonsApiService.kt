package com.gilorroristore.thesimpsons.data.network

import com.gilorroristore.thesimpsons.data.network.model.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpsonsApiService {

    @GET("characters")
    suspend fun getAllCharacters(): CharactersResponse

    @GET("characters")
    suspend fun loadNextPage(@Query("page") page: Int): CharactersResponse
}