package com.sirfilbido.filbidomovies.data.services.genres

import com.sirfilbido.filbidomovies.data.services.genres.response.GenresResponse
import retrofit2.http.GET

interface GenresService {

    @GET("genre/movie/list")
    suspend fun getAllGenres(): GenresResponse
}