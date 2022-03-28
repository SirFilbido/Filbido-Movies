package com.sirfilbido.filbidomovies.data.repository.genre

import android.os.RemoteException
import com.sirfilbido.filbidomovies.data.model.Genre
import com.sirfilbido.filbidomovies.data.services.genres.GenresService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GenreRepositoryImpl(
    private val service: GenresService,
) : GenreRepository {
    override suspend fun getGenres(): Flow<List<Genre>> = flow {
        try {
            val genresList = service.getAllGenres()
            emit(genresList.genres)
        } catch (error: HttpException) {
            throw RemoteException("Unable to retrieve genres in TMDB")
        }
    }
}