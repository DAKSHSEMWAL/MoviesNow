package com.dakshsemwal.movienow.data.repository

import com.dakshsemwal.movienow.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getMovie() = apiHelper.getMovie()

}