package com.example.findmovies

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.find_movies_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FindMoviesFragment : Fragment() {
    var listOfMovies: MutableList<Movie> = mutableListOf()
    lateinit var recyclerAdapter: RecyclerAdapter

    companion object {
        fun newInstance() = FindMoviesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.find_movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()

        btnSearch.setOnClickListener {
            if (edtSearch.text.isNotEmpty()) {
                searchMovies(edtSearch.text.toString())
            } else {
                println("No value to search")
            }
        }
    }

    private fun initRecyclerView() {
        recyclerAdapter = RecyclerAdapter(listOfMovies)
        recyclerView.apply {
            hasFixedSize()
            layoutManager = GridLayoutManager(context, 1)
            adapter = recyclerAdapter
        }
    }

    fun searchMovies(searchText: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
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
                        }
                    }
                    println(listOfMovies)
                    refreshRecyclerView()
                }
            }
        })
    }

    fun resetList() {
        listOfMovies.clear()
    }

    fun refreshRecyclerView() {
        recyclerAdapter.notifyDataSetChanged()
    }
}