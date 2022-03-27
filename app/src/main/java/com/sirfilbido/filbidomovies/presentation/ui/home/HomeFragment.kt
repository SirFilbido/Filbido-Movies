package com.sirfilbido.filbidomovies.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sirfilbido.filbidomovies.data.model.Movie
import com.sirfilbido.filbidomovies.databinding.HomeFragmentBinding
import com.sirfilbido.filbidomovies.presentation.adapter.home.MovieListAdapter
import com.sirfilbido.filbidomovies.presentation.ui.State
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by sharedViewModel()
    private val binding: HomeFragmentBinding by lazy {
        HomeFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initBinding()
        initSnackbar()
        initRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMovies()
    }

    private fun initSnackbar() {
        viewModel.snackbar.observe(viewLifecycleOwner) {
            it?.let { errorMessage ->
                Snackbar.make(
                    binding.root, errorMessage,
                    Snackbar.LENGTH_LONG
                ).show()
                viewModel.onSnackBarShown()
            }
        }
    }

    private fun initRecyclerView() {
        val adapter = MovieListAdapter(MovieListAdapter.OnClickListener { saveAndOpenItem(it) })
        binding.homeRv.adapter = adapter

        viewModel.listMovie.observe(viewLifecycleOwner) {
            when (it) {
                State.Loading -> {
                    viewModel.showProgressBar()
                }
                is State.Error -> {
                    viewModel.hideProgressBar()
                }
                is State.Success -> {
                    viewModel.hideProgressBar()
                    adapter.submitList(it.result)
                }
            }
        }

    }

    private fun saveAndOpenItem(movie: Movie) {
        viewModel.selectItem(movie)
        val action = HomeFragmentDirections.actionHomeFragmentToDetailMovieFragment()
        findNavController().navigate(action)
    }

    private fun initBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

}