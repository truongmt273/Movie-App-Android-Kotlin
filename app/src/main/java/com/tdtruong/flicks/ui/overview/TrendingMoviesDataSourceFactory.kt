package com.tdtruong.flicks.ui.overview

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.tdtruong.flicks.models.Movie
import com.tdtruong.flicks.network.api.ApiService
import com.tdtruong.flicks.network.paging.TrendingMoviesDataSource
import io.reactivex.disposables.CompositeDisposable

class TrendingMoviesDataSourceFactory (
    private val compositeDisposable: CompositeDisposable,
    private val apiService: ApiService)
    : DataSource.Factory<Int, Movie>() {

    val trendingMoviesDataSourceLiveData = MutableLiveData<TrendingMoviesDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val trendingMoviesDataSource = TrendingMoviesDataSource(apiService, compositeDisposable)
        trendingMoviesDataSourceLiveData.postValue(trendingMoviesDataSource)
        return trendingMoviesDataSource
    }
}