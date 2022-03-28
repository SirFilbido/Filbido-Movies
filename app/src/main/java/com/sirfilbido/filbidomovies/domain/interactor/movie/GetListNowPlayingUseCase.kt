package com.sirfilbido.filbidomovies.domain.interactor.movie

import android.os.Build
import androidx.annotation.RequiresApi
import com.sirfilbido.filbidomovies.data.model.Genre
import com.sirfilbido.filbidomovies.data.model.Movie
import com.sirfilbido.filbidomovies.data.repository.movie.MovieRepository
import com.sirfilbido.filbidomovies.data.services.movie.response.MovieResponse
import com.sirfilbido.filbidomovies.data.services.movie.response.toMovie
import com.sirfilbido.filbidomovies.domain.interactor.UseCase.NoParam
import com.sirfilbido.filbidomovies.domain.interactor.genre.GetGenresUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single

class GetListNowPlayingUseCase(
    private val repository: MovieRepository,
    private val getGenresUseCase: GetGenresUseCase,
) : NoParam<List<Movie>>() {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun execute(): Flow<List<Movie>> = try {
        val movies = repository.getListNowPlaying().single()
        val genres = getGenresUseCase.execute().single()
        toMovies(movies, genres)
    } catch (error: Exception) {
        println(error.stackTrace.toString())
        flow { emit(listOf()) }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun toMovies(
        movies: List<MovieResponse>,
        genres: List<Genre>,
    ): Flow<List<Movie>> {

        val list = mutableListOf<Movie>()
        movies.map { movie -> list.add(movie.toMovie(genres)) }

        return flow { emit(list) }
    }

}