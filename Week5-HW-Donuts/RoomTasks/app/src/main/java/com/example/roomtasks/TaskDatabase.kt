package com.example.roomtasks

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [Task::class],version = 3,exportSchema = false)
abstract class TaskDatabase:RoomDatabase() {
    abstract val taskDao:TaskDao//tell room that you want to use the dataaccess methods specified in TaskDao

    companion object {
        @Volatile
        private var INSTANCE : TaskDatabase? = null

        fun getInstance(context:Context):TaskDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "donuts_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}