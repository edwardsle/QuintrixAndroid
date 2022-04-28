package com.example.findmovies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val apiUrl = "https://www.omdbapi.com/"

class FindMoviesViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var keyword = ""
    private  var result : MutableLiveData<String> = MutableLiveData()
    var listOfMovies: MutableList<Movie> = mutableListOf()

    fun setKeyword(value: String) {
        this.keyword = value
        fetchData(value)
    }

    fun getResult():MutableLiveData<String>{
        return result
    }

    fun fetchData( input: String){
        retrofitSearch(input)
    }

    fun retrofitSearch(searchText: String) {
        var strMovies : String = ""
        val retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val resultSearch = retrofit.create(IMDBApi::class.java).search(searchText)
        resultSearch.enqueue(object : Callback<Search> {
            override fun onFailure(call: Call<Search>, t: Throwable) {
                Log.e("Search", "Error: ${t}")
            }

            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                resetList()
                val allSearch = response.body()?.resultSearch
                allSearch?.let {
                    for (movie in allSearch) {
                        if (movie.title != null && movie.poster != null) {
                            listOfMovies.add(movie)
                            strMovies = strMovies + movie.title + " (" + movie.year + ")" + "\n"
                        }
                        Log.i("Response : ", "$movie")
                    }
                    result.setValue(strMovies)
                }
            }
        })
    }

    fun resetList() {
        listOfMovies.clear()
    }
}