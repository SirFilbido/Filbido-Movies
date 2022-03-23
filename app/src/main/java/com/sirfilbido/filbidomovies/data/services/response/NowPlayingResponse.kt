package com.sirfilbido.filbidomovies.data.services.response

import com.sirfilbido.filbidomovies.data.model.Movie

class NowPlayingResponse(
    val page: Long,
    val results: List<Movie>,
    val totalPages: Long,
    val totalResults: Long,
)