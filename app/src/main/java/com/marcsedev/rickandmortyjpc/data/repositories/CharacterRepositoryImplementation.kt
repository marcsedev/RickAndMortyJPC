package com.marcsedev.rickandmortyjpc.data.repositories

import com.marcsedev.rickandmortyjpc.data.Result
import com.marcsedev.rickandmortyjpc.data.source.remote.RickMortyApi
import com.marcsedev.rickandmortyjpc.data.source.remote.dto.toCharacter
import com.marcsedev.rickandmortyjpc.data.source.remote.dto.toListCharacters
import com.marcsedev.rickandmortyjpc.domain.model.Character
import com.marcsedev.rickandmortyjpc.domain.model.Characters
import com.marcsedev.rickandmortyjpc.domain.repositories.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class CharacterRepositoryImplementation @Inject constructor(
    private val api: RickMortyApi
): CharacterRepository{

        override fun getAllCharacters(page: Int): Flow<Result<List<Characters>>> = flow{
            emit(Result.Loading())
            try {
                val response = api.getAllCharacters(page).toListCharacters()
                emit(Result.Success(response))
            } catch (e: HttpException) {
                emit(
                    Result.Error(
                    message = "Oops, something went wrong",
                    data = null
                ))
            } catch (e: IOException) {
                emit(
                    Result.Error(
                    message = "Couldn't reach server, check your internet connection",
                    data = null
                ))
            }
        }

        override suspend fun getCharacter(id: Int): Result<Character> {
            val response = try {
                api.getCharacter(id)
            } catch (e: Exception) {
                return Result.Error("An unknown error occurred")
            }
            return Result.Success(response.toCharacter())
        }
    }