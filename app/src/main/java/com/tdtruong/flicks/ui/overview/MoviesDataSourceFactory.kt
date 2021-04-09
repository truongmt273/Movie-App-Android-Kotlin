package com.tdtruong.flicks.ui.overview

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.tdtruong.flicks.models.Movie
import com.tdtruong.flicks.network.api.ApiService
import com.tdtruong.flicks.network.paging.MoviesDataSource
import io.reactivex.disposables.CompositeDisposable

class MoviesDataSourceFactory (
    private val compositeDisposable: CompositeDisposable,
    private val apiService: ApiService)
    : DataSource.Factory<Int, Movie>() {

    val moviesDataSourceLiveData = MutableLiveData<MoviesDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val moviesDataSource = MoviesDataSource(apiService, compositeDisposable)
        moviesDataSourceLiveData.postValue(moviesDataSource)
        return moviesDataSource
    }
}