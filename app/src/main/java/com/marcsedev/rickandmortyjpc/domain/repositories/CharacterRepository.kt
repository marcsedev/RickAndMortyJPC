package com.marcsedev.rickandmortyjpc.domain.repositories

import kotlinx.coroutines.flow.Flow
import com.marcsedev.rickandmortyjpc.data.Result
import com.marcsedev.rickandmortyjpc.domain.model.Characters
import com.marcsedev.rickandmortyjpc.domain.model.Character

interface CharacterRepository {

    fun getAllCharacters(page: Int): Flow<Result<List<Characters>>>

    suspend fun getCharacter(id: Int): Result<Character>
}