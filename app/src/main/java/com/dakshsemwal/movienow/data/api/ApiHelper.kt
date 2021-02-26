package com.dakshsemwal.movienow.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getMovie() = apiService.getMovie()

}