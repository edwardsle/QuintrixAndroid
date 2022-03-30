package com.example.roomtasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.roomtasks.databinding.FragmentTasksBinding
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


//import com.example.viewmodelexample.BR.myViewModel //BR = binding root




/**
 * A simple [Fragment] subclass.
 * Use the [TasksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TasksFragment : Fragment() {

    private var _binding : FragmentTasksBinding? = null
    private val binding get() = _binding
    //lateinit var binding: FragmentTasksBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTasksBinding.inflate(inflater,container,false)
        val view = binding!!.root
        //     _binding = LayoutInflater.container: ViewGroup? .inflate(inflater, R.layout.fragment_tasks, container, false)
        //binding.setLifecycleOwner(this)
        val application= requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        val viewModelFactory = TasksViewModelFactory(dao)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(TasksViewModel::class.java)
        binding!!.viewModel = viewModel
        binding!!.lifecycleOwner=viewLifecycleOwner//set layout's lifecycleOwner so that it can respond to data updates

        return view
    }


}