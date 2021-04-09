package com.tdtruong.flicks.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tdtruong.flicks.models.Movie

class MovieDetailViewModelFactory(
    private val movieId: Int,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(movieId, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}