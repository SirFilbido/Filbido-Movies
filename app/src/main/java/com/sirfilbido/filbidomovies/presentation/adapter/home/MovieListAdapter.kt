package com.sirfilbido.filbidomovies.presentation.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sirfilbido.filbidomovies.data.model.Movie
import com.sirfilbido.filbidomovies.databinding.ItemMovieBinding

class MovieListAdapter : ListAdapter<Movie, MovieListAdapter.ImageViewHolder>(ImageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ImageViewHolder(private val _binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(_binding.root) {

        companion object {
            fun from(parent: ViewGroup): ImageViewHolder {
                val binding: ItemMovieBinding = ItemMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ImageViewHolder(binding)
            }
        }

        fun bind(item: Movie) {
            _binding.movie = item
        }

    }

    private class ImageDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}