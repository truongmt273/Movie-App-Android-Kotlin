package com.tdtruong.flicks.network.api

import com.tdtruong.flicks.models.Movies
import com.tdtruong.flicks.models.Movie
import com.tdtruong.flicks.utils.API_KEY
import com.tdtruong.flicks.utils.BASE_URL
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

interface ApiService {
    @GET("movie/now_playing?api_key=$API_KEY")
    fun getListMovie(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Single<Movies>

    @GET("trending/movie/day?api_key=$API_KEY")
    fun getTrendingMovie(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Single<Movies>

    @GET("movie/{id}?api_key=$API_KEY&append_to_response=videos")
    suspend fun getMovie(
        @Path("id") id: Int
    ): Movie

    companion object {
        fun getService(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}

object MoviesApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}