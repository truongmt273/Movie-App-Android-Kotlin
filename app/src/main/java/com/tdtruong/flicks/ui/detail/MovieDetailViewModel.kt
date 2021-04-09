package com.tdtruong.flicks.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tdtruong.flicks.network.api.MoviesApi
import com.tdtruong.flicks.models.Movie
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieId: Int, app: Application) : AndroidViewModel(app) {
    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie>
        get() = _selectedMovie

    init {
        fetchSingleMovie()
    }

    private fun fetchSingleMovie() {
        viewModelScope.launch {
            try {
                _selectedMovie.value = MoviesApi.retrofitService.getMovie(id = movieId)
                Log.d("DETAIL MOVIE", _selectedMovie.value.toString())
            } catch (e: Exception) {
                print(e)
            }
        }
    }
}