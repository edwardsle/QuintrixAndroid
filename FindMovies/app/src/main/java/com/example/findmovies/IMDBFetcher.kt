package com.example.findmovies

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IMDBFetcher {

    private val imdbApi : IMDBApi
    val apiUrl = "https://www.omdbapi.com/"

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        imdbApi = retrofit.create(IMDBApi::class.java)
    }
}