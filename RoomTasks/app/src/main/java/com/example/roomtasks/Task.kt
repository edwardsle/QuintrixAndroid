package com.example.roomtasks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var taskId:Long =0L,

    @ColumnInfo(name = "task_name")
//@ColumnInfo is needed only if you want your column name to be different from your property name
    var taskName:String="",

    @ColumnInfo(name = "task_done")
    var taskDone:Boolean=false
)