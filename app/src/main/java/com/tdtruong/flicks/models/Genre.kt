package com.tdtruong.flicks.models


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Genre(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = ""
)