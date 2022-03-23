package com.sirfilbido.filbidomovies.data.repository.genre

import com.sirfilbido.filbidomovies.data.model.Genre
import kotlinx.coroutines.flow.Flow

interface GenreRepository {

    suspend fun getGenres(): Flow<List<Genre>>
}