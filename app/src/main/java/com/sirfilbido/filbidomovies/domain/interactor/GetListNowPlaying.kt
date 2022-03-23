package com.sirfilbido.filbidomovies.domain.interactor

import com.sirfilbido.filbidomovies.data.model.Movie
import com.sirfilbido.filbidomovies.data.repository.MovieRepository
import com.sirfilbido.filbidomovies.domain.interactor.UseCase.NoParam
import kotlinx.coroutines.flow.Flow

class GetListNowPlayingUseCase(
    private val _repository: MovieRepository,
) : NoParam<List<Movie>>() {

    override suspend fun execute(): Flow<List<Movie>> {
        return _repository.getListNowPlaying()
    }

}