package com.sirfilbido.filbidomovies.data.services.movie.response

class NowPlayingResponse(
    val page: Long,
    val results: List<MovieResponse>,
    val totalPages: Long,
    val totalResults: Long,
)