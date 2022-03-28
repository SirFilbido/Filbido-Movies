package com.sirfilbido.filbidomovies.domain.interactor.genre

import com.sirfilbido.filbidomovies.data.model.Genre
import com.sirfilbido.filbidomovies.data.repository.genre.GenreRepository
import com.sirfilbido.filbidomovies.domain.interactor.UseCase.NoParam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetGenresUseCase(
    private val repository: GenreRepository,
) : NoParam<List<Genre>>() {

    override suspend fun execute(): Flow<List<Genre>> = try {
        repository.getGenres()
    } catch (error: Exception) {
        flow { emit(listOf()) }
    }
}