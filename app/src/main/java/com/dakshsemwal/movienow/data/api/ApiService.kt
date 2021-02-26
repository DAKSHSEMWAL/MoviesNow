package com.dakshsemwal.movienow.data.api

import com.dakshsemwal.movienow.model.Movie
import retrofit2.http.GET

interface ApiService {

    @GET("moviedata.json")
    suspend fun getMovie(): List<Movie>

}