package com.dakshsemwal.movienow.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Info (
    @SerializedName("directors")
    var directors: List<String>?,
    @SerializedName("release_date")
    var releaseDate: String?,
    @SerializedName("rating")
    var rating: Double? ,
    @SerializedName("genres")
    var genres: List<String>?,
    @SerializedName("image_url")
    var imageUrl: String?,
    @SerializedName("plot")
    var plot: String?,
    @SerializedName("rank")
    var rank: Int?,
    @SerializedName("running_time_secs")
    var runningTimeSecs: Int?,
    @SerializedName("actors")
    var actors: List<String>?
)