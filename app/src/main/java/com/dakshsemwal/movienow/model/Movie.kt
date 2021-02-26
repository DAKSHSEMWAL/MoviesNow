package com.dakshsemwal.movienow.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("year")
    var year: Int?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("info")
    var info: Info?
)