package com.sirfilbido.filbidomovies.domain.interactor.movie

import android.os.Build
import androidx.annotation.RequiresApi
import com.sirfilbido.filbidomovies.data.model.Genre
import com.sirfilbido.filbidomovies.data.model.Movie
import com.sirfilbido.filbidomovies.data.repository.movie.MovieRepository
import com.sirfilbido.filbidomovies.data.services.movie.response.MovieResponse
import com.sirfilbido.filbidomovies.domain.interactor.UseCase.NoParam
import com.sirfilbido.filbidomovies.domain.interactor.genre.GetGenresUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GetListNowPlayingUseCase(
    private val _repository: MovieRepository,
    private val _getGenresUseCase: GetGenresUseCase,
) : NoParam<List<Movie>>() {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun execute(): Flow<List<Movie>> {
        val movies = _repository.getListNowPlaying().first()
        val genres = _getGenresUseCase.execute().first()
        return toMovies(movies, genres)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun toMovies(
        movies: List<MovieResponse>,
        genres: List<Genre>,
    ): Flow<List<Movie>> {

        val list = mutableListOf<Movie>()

        movies.map { movie ->

            val listGenres = mutableListOf<Genre>()
            movie.genres.forEach { idGenres ->
                listGenres.add(genres.find { genre -> genre.id == idGenres }!!)
            }

            list.add(
                Movie(
                    id = movie.id,
                    poster = movie.poster,
                    title = movie.title,
                    overview = movie.overview,
                    releaseDate = LocalDate.parse(
                        movie.releaseDate,
                        DateTimeFormatter.ISO_DATE
                    ),
                    genres = listGenres
                )
            )
        }

        return flow { emit(list) }
    }

}