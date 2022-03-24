package com.sirfilbido.filbidomovies.presentation.ui.home

import android.os.RemoteException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirfilbido.filbidomovies.data.model.Movie
import com.sirfilbido.filbidomovies.domain.interactor.movie.GetListNowPlayingUseCase
import com.sirfilbido.filbidomovies.presentation.State
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val _getListNowPlayingUseCase: GetListNowPlayingUseCase,
) : ViewModel() {

    private val _listMovie = MutableLiveData<State<List<Movie>>>()
    val listMovie: LiveData<State<List<Movie>>>
        get() = _listMovie

    fun fetchMovies() {
        viewModelScope.launch {
            _getListNowPlayingUseCase()
                .onStart {
                    _listMovie.postValue(State.Loading)
//                    delay(800)
                }.catch {
                    with(RemoteException("Could not connect to API")) {
                        _listMovie.postValue(State.Error(this))
                    }
                }.collect {
                    _listMovie.postValue(State.Success(it))
                }
        }
    }
}