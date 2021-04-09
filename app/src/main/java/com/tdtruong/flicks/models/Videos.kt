package com.tdtruong.flicks.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("results")
    val results: List<Result> = listOf()
)