package com.sirfilbido.filbidomovies.data.model

import com.squareup.moshi.Json

data class Movie(
    val id: Long,
    @Json(name = "poster_path") val poster: String,
    val title: String,
    val overview: String,
    @Json(name = "genre_ids") val genres: List<Long>,
)
