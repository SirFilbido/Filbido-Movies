package com.sirfilbido.filbidomovies.data.repository.movie

import com.sirfilbido.filbidomovies.data.services.movie.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getListNowPlaying(): Flow<List<MovieResponse>>
}