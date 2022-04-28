package com.example.findmovies

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.find_movies_fragment.*



class FindMoviesFragment : Fragment() {
    companion object {
        fun newInstance() = FindMoviesFragment()
    }

    private lateinit var viewModel: FindMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.find_movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FindMoviesViewModel::class.java)
        // TODO: Use the ViewModel
        class resultObserver : Observer<String> {
            override fun onChanged(result: String?) {
                lsDisplay.text = result.toString()
            }
        }

        val myResultObserver = resultObserver()
        viewModel.getResult().observe(viewLifecycleOwner,myResultObserver)

        btnSearch.setOnClickListener {

            if (edtSearch.text.isNotEmpty()) {

                viewModel.setKeyword(edtSearch.text.toString())
                //don't need this
                //resultText.text = viewModel.getResult().toString()

            } else {

                lsDisplay.text = "No Value"

            }
        }
    }
}