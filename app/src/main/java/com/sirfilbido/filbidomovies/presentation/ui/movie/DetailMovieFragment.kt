package com.sirfilbido.filbidomovies.presentation.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sirfilbido.filbidomovies.databinding.MovieDetailFragmentBinding
import com.sirfilbido.filbidomovies.presentation.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DetailMovieFragment : Fragment() {

    private val viewModel: HomeViewModel by sharedViewModel()
    private val binding: MovieDetailFragmentBinding by lazy {
        MovieDetailFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.viewModel = viewModel
        return binding.root
    }
}