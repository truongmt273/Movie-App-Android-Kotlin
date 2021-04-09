package com.tdtruong.flicks.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Movie(
    @SerializedName("genres")
    val genres: @RawValue List<Genre> = listOf(),
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("production_countries")
    val productionCountries: @RawValue List<ProductionCountry> = listOf(),
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("runtime")
    val runtime: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("video")
    val video: Boolean = false,
    @SerializedName("videos")
    val videos: @RawValue Videos,
    @SerializedName("vote_average")
    val voteAverage: Float = 0.0F,
    @SerializedName("vote_count")
    val voteCount: Int = 0
) : Parcelable {}