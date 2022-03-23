package com.sirfilbido.filbidomovies.data.repository

import android.os.RemoteException
import com.sirfilbido.filbidomovies.data.model.Movie
import com.sirfilbido.filbidomovies.data.services.movie.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MovieRepositoryImpl(
    private val service: MovieService,
) : MovieRepository {

    override suspend fun getListNowPlaying(): Flow<List<Movie>> = flow {
        try {
            val imageList = service.getListNowPlaying()
            emit(imageList.results)
        } catch (error: HttpException) {
            throw RemoteException("Unable to retrieve images")
        }
    }
}