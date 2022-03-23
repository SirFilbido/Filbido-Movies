package com.sirfilbido.filbidomovies.data.services.movie

import com.sirfilbido.filbidomovies.data.services.movie.response.NowPlayingResponse
import retrofit2.http.GET

interface MovieService {

    @GET("movie/now_playing")
    suspend fun getListNowPlaying(): NowPlayingResponse
}