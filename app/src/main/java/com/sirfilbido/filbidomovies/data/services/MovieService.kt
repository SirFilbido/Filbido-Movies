package com.sirfilbido.filbidomovies.data.services

import com.sirfilbido.filbidomovies.data.services.response.NowPlayingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    suspend fun getListNowPlaying(
        @Query("api_key") apiKey: String?,
    ): NowPlayingResponse
}