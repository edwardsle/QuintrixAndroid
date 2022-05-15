package com.example.findmovies

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val OMBD_API_KEY = "17566353"

interface IMDBApi {
    @GET("/")
    fun search(@Query("s") searchText: String, @Query("apikey") ombd_api_key: String = OMBD_API_KEY): Call<Search>
}