package com.sirfilbido.filbidomovies.presentation.binding.home

import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.sirfilbido.filbidomovies.data.model.Movie
import java.time.format.DateTimeFormatter

@BindingAdapter("moviePoster")
fun ImageView.setMoviePoster(movie: Movie?) {
    movie?.let {

        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(this).load(movie.poster)
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