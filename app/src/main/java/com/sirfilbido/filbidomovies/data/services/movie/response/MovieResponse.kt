package com.sirfilbido.filbidomovies.data.services.movie.response

import android.os.Build
import androidx.annotation.RequiresApi
import com.sirfilbido.filbidomovies.data.model.Genre
import com.sirfilbido.filbidomovies.data.model.Movie
import com.squareup.moshi.Json
import java.time.LocalDate

class MovieResponse(
    val id: Long,
    @Json(name = "poster_path") val poster: String,
    val title: String,
    @Json(name = "release_date") val releaseDate: LocalDate,
    val overview: String,
    @Json(name = "genre_ids") val genres: List<Long>,
)

@RequiresApi(Build.VERSION_CODES.O)
fun MovieResponse.toMovie(genres: List<Genre>): Movie {

    val listGenres = mutableListOf<Genre>()
    this.genres.forEach { idGenres ->
        genres.find { genre -> genre.id == idGenres }?.let { listGenres.add(it) }
    }

    return Movie(
        id = this.id,
        poster = this.poster,
        title = this.title,
        overview = this.overview,
        releaseDate = this.releaseDate,
        genres = listGenres
    )
}