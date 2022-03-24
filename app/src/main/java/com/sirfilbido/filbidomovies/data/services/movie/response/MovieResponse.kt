package com.sirfilbido.filbidomovies.data.services.movie.response

import com.sirfilbido.filbidomovies.data.model.Movie
import com.squareup.moshi.Json

class MovieResponse(
    val id: Long,
    @Json(name = "poster_path") val poster: String,
    val title: String,
    val overview: String,
    @Json(name = "genre_ids") val genres: List<Long>,
)