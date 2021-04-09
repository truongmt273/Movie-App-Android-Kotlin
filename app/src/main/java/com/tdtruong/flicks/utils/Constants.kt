package com.tdtruong.flicks.utils

const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";
const val BASE_URL = "https://api.themoviedb.org/3/";
const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500";
const val BASE_TRENDING = "https://api.themoviedb.org/3/trending/"
const val BASE_YOUTUBE_URL = "https://www.youtube.com/watch?v="

enum class State {
    DONE, LOADING, ERROR
}