package com.marcsedev.rickandmortyjpc.data.source.remote

import com.marcsedev.rickandmortyjpc.data.source.remote.dto.CharacterDto
import com.marcsedev.rickandmortyjpc.data.source.remote.dto.CharactersDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

abstract class RickMortyApi {
    //manejar las llamadas

    @GET("character/")
    abstract suspend fun getAllCharacters(
        @Query("page") page: Int
    ): CharactersDto

    @GET("character/{id}")
    abstract suspend fun getCharacter(
        @Path("id") id: Int
    ): CharacterDto

}