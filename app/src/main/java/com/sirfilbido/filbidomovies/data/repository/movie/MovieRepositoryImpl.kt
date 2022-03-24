package com.sirfilbido.filbidomovies.data.repository.movie

import android.os.RemoteException
import com.sirfilbido.filbidomovies.data.services.movie.MovieService
import com.sirfilbido.filbidomovies.data.services.movie.response.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MovieRepositoryImpl(
    private val _service: MovieService,
) : MovieRepository {

    override suspend fun getListNowPlaying(): Flow<List<MovieResponse>> = flow {
        try {
            val imageList = _service.getListNowPlaying()
            emit(imageList.results)
        } catch (error: HttpException) {
            throw RemoteException("Unable to retrieve movies in TMDB")
        }
    }
}
