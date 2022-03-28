package com.sirfilbido.filbidomovies.presentation.ui.home

import android.os.RemoteException
import androidx.lifecycle.*
import com.sirfilbido.filbidomovies.data.model.Movie
import com.sirfilbido.filbidomovies.domain.interactor.movie.GetListNowPlayingUseCase
import com.sirfilbido.filbidomovies.presentation.ui.State
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getListNowPlayingUseCase: GetListNowPlayingUseCase,
) : ViewModel() {

    private val _progressBarVisible = MutableLiveData(false)
    val progressBarVisible: LiveData<Boolean> = _progressBarVisible

    fun showProgressBar() {
        _progressBarVisible.value = true
    }

    fun hideProgressBar() {
        _progressBarVisible.value = false
    }

    private val _snackbar = MutableLiveData<String?>(null)
    val snackbar: LiveData<String?> = _snackbar

    fun onSnackBarShown() {
        _snackbar.value = null
    }

    private val _listMovie = MutableLiveData<State<List<Movie>>>()
    val listMovie: LiveData<State<List<Movie>>> = _listMovie

    fun fetchMovies() {
        viewModelScope.launch {
            getListNowPlayingUseCase()
                .onStart {
                    _listMovie.postValue(State.Loading)
                    delay(800)
                }.catch {
                    with(RemoteException("Could not connect to TMDB")) {
                        _listMovie.postValue(State.Error(this))
                    }
                }.collect {
                    _listMovie.postValue(State.Success(it))
                }
        }
    }

    private val _mutableSelectedItem = MutableLiveData<Movie>()
    val selectedItem: LiveData<Movie> = _mutableSelectedItem

    fun selectItem(movie: Movie) {
        _mutableSelectedItem.value = movie
    }

    val helloText = Transformations.map(listMovie) {
        listMovie.let {
            when (it.value) {
                State.Loading -> {
                    "🎬 Loading movies from TMDB..."
                }
                is State.Error -> {
                    "We have a problem! :'("
                }
                else -> {
                    ""
                }
            }
        }
    }
}