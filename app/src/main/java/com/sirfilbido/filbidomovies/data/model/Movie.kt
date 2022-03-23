package com.sirfilbido.filbidomovies.data.model

data class Movie(
    val id: Long,
    val poster: String,
    val title: String,
    val overview: String,
    val genres: List<Genre>,
)
