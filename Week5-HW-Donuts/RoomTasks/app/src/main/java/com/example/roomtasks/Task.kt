package com.example.roomtasks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "donut_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var donutId:Long =0L,

    @ColumnInfo(name = "donut_quantity")
    var donutQuantity:Int = 0
)