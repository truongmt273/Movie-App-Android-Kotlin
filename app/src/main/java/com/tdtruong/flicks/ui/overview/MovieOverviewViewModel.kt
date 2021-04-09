package com.tdtruong.flicks.ui.overview

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tdtruong.flicks.models.Movie
import com.tdtruong.flicks.network.api.ApiService
import com.tdtruong.flicks.network.paging.MoviesDataSource
import com.tdtruong.flicks.utils.State
import io.reactivex.disposables.CompositeDisposable

class MovieOverviewViewModel : ViewModel() {

    private val apiService = ApiService.getService()
    var moviesList: LiveData<PagedList<Movie>>
    var trendingMoviesList: LiveData<PagedList<Movie>>
    private val compositeDisposable = CompositeDisposable()
    private val compositeDisposable_second = CompositeDisposable()
    private val pageSize = 5
    private val moviesDataSourceFactory: MoviesDataSourceFactory
    private val trendingMoviesDataSourceFactory: TrendingMoviesDataSourceFactory

    val _selectedMovie = MutableLiveData<Movie>()
    fun select(item: Movie) {
        _selectedMovie.value = item
    }


    init {
        trendingMoviesDataSourceFactory =
            TrendingMoviesDataSourceFactory(compositeDisposable_second, apiService)
        val config_trending = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()
        trendingMoviesList = LivePagedListBuilder<Int, Movie>(
            trendingMoviesDataSourceFactory,
            config_trending
        ).build()

        moviesDataSourceFactory = MoviesDataSourceFactory(compositeDisposable, apiService)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()
        moviesList = LivePagedListBuilder<Int, Movie>(moviesDataSourceFactory, config).build()
    }

    fun getState(): LiveData<State> = Transformations.switchMap<MoviesDataSource,
            State>(moviesDataSourceFactory.moviesDataSourceLiveData, MoviesDataSource::state)

    fun retry() {
        moviesDataSourceFactory.moviesDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return moviesList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
        compositeDisposable_second.dispose()
    }

//    private fun fetchMovies() {
//        viewModelScope.launch {
//            try {
//                _listMovie.value = MoviesApi.retrofitService.getListMovie()
//                _movies.value = _listMovie.value!!.results
//            } catch (e: Exception) {
//                print(e)
//            }
//        }
//    }
//
//    fun displayMovieDetails(movie: Movie) {
//
//    }
}
