package com.sirfilbido.filbidomovies.presentation.binding.movie

import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.sirfilbido.filbidomovies.data.model.Movie
import java.time.format.DateTimeFormatter

private const val BASE_URL_POSTER = "https://image.tmdb.org/t/p/original/"

@BindingAdapter("moviePoster")
fun ImageView.setMoviePoster(movie: Movie?) {
    movie?.let {

        this.clipToOutline = true

        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(this).load("$BASE_URL_POSTER${movie.poster}")
            .placeholder(circularProgressDrawable).into(this)
    }
}

@BindingAdapter("movieTitle")
fun TextView.setMovieTitle(movie: Movie?) {
    movie?.let {
        text = it.title
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter("movieReleaseDate")
fun TextView.setMovieReleaseDate(movie: Movie?) {
    movie?.let {
        val formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        text = movie.releaseDate.format(formatters)
    }
}

@BindingAdapter("movieGenre")
fun TextView.setMovieGenre(movie: Movie?) {
    movie?.let {
        val nameGenres = it.genres.map { genre -> genre.name }
        text = nameGenres.joinToString(", ")
    }
}

@BindingAdapter("movieOverview")
fun TextView.setMovieOverview(movie: Movie?) {
    movie?.let { text = it.overview }
}