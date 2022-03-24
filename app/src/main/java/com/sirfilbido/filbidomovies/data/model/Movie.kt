package com.sirfilbido.filbidomovies.data.model

import java.time.LocalDate

data class Movie(
    val id: Long,
    val poster: String,
    val title: String,
    val releaseDate: LocalDate,
    val overview: String,
    val genres: List<Genre>,
)
