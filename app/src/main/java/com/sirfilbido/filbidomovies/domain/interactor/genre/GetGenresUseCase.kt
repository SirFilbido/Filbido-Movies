package com.sirfilbido.filbidomovies.domain.interactor.genre

import com.sirfilbido.filbidomovies.data.model.Genre
import com.sirfilbido.filbidomovies.data.repository.genre.GenreRepository
import com.sirfilbido.filbidomovies.domain.interactor.UseCase.NoParam
import kotlinx.coroutines.flow.Flow

class GetGenresUseCase(
    private val _repository: GenreRepository,
) : NoParam<List<Genre>>() {

    override suspend fun execute(): Flow<List<Genre>> {
        return _repository.getGenres()
    }
}