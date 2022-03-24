package com.sirfilbido.filbidomovies.domain.interactor.movie

import com.sirfilbido.filbidomovies.data.model.Genre
import com.sirfilbido.filbidomovies.data.model.Movie
import com.sirfilbido.filbidomovies.data.repository.movie.MovieRepository
import com.sirfilbido.filbidomovies.data.services.movie.response.MovieResponse
import com.sirfilbido.filbidomovies.domain.interactor.UseCase.NoParam
import com.sirfilbido.filbidomovies.domain.interactor.genre.GetGenresUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetListNowPlayingUseCase(
    private val _repository: MovieRepository,
    private val _getGenresUseCase: GetGenresUseCase,
) : NoParam<List<Movie>>() {

    override suspend fun execute(): Flow<List<Movie>> {
        val movies = _repository.getListNowPlaying()
        val genres = _getGenresUseCase.execute()
        return toMovies(movies, genres)
    }

    private fun toMovies(
        movies: Flow<List<MovieResponse>>,
        genres: Flow<List<Genre>>,
    ): Flow<List<Movie>> {

        val list = mutableListOf<Movie>()

        movies.map { listMovies ->
            listMovies.map { movie ->

                val listGenres = mutableListOf<Genre>()
                movie.genres.forEach { idGenres ->
                    genres.map { listGenres.add(it.find { genre -> genre.id == idGenres }!!) }
                }

                list.add(
                    Movie(
                        id = movie.id,
                        poster = movie.poster,
                        title = movie.title,
                        overview = movie.title,
                        releaseDate = movie,
                        genres = listGenres
                    )
                )
            }
        }

        println(list)

        return flow { emit(list) }
    }

}