package com.example.roomtasks

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Insert
    suspend  fun insert(task:Task)

    @Update
    suspend   fun update(task: Task)

    @Delete
    suspend fun delete(task:Task)

    @Query("SELECT * FROM donut_table WHERE donutId= :taskId")
    fun get(taskId: Long):LiveData<Task>

    @Query("SELECT * FROM donut_table ORDER BY donutId DESC LIMIT 1")
    fun getLastEntry():LiveData<Task>

    @Query("SELECT * FROM donut_table ORDER BY donutId DESC")
    fun getAll():LiveData<List<Task>>


}