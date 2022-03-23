package com.sirfilbido.filbidomovies.data.repository.movie

import com.sirfilbido.filbidomovies.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getListNowPlaying(): Flow<List<Movie>>
}