package com.sirfilbido.filbidomovies.domain.genre

import com.sirfilbido.filbidomovies.data.model.Genre

object GenresFactory {

    val genres = listOf(
        Genre(
            id = 28,
            name = "Action"
        ),
        Genre(
            id = 12,
            name = "Adventure"
        ),
        Genre(
            id = 16,
            name = "Animation"
        ),
        Genre(
            id = 35,
            name = "Comedy"
        ),
        Genre(
            id = 14,
            name = "Fantasy"
        ),
    )
}