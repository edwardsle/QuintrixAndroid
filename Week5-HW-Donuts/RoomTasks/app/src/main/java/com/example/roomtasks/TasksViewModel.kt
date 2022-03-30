package com.example.roomtasks


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import  androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel(val dao: TaskDao):ViewModel() {
    var newTaskName = ""
    private val tasks : LiveData<Task> = dao.getLastEntry()

    val tasksString = Transformations.map(tasks){

        tasks->formatTasks((tasks))
    }

    fun formatTasks(tasks:Task):String{
        return tasks.donutQuantity.toString()
    }

    fun addTask(){
        viewModelScope.launch {
            val task = Task()
            task.donutQuantity = Integer.parseInt(newTaskName)
            dao.insert(task)
        }
    }
}