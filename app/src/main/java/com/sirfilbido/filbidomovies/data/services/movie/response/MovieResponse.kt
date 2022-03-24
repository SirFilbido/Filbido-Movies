package com.sirfilbido.filbidomovies.data.services.movie.response

import com.squareup.moshi.Json

class MovieResponse(
    val id: Long,
    @Json(name = "poster_path") val poster: String,
    val title: String,
    val releaseDate: String,
    val overview: String,
    @Json(name = "genre_ids") val genres: List<Long>,
)