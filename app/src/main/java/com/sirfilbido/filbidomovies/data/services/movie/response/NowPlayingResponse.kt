package com.sirfilbido.filbidomovies.data.services.movie.response

import com.squareup.moshi.Json

class NowPlayingResponse(
    val page: Long,
    val results: List<MovieResponse>,
    @Json(name = "total_pages") val totalPages: Long,
    @Json(name = "total_results") val totalResults: Long,
)