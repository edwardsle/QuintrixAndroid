package com.example.simpletable

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ( "
                + ID_COL + " INTEGER PRIMARY KEY, "
                + FIRSTNAME_COL + " TEXT,"
                + LASTNAME_COL + " TEXT, "
                + REWARDS_COL + " INTEGER" + ")")

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addPeople(uid: Int, firstname: String, lastname: String, rewards: Int) {
        val values = ContentValues()
        values.put(ID_COL, uid)
        values.put(FIRSTNAME_COL, firstname)
        values.put(LASTNAME_COL, lastname)
        values.put(REWARDS_COL, rewards)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getPeople(uid: Int): Cursor? {
        println("Retrieve from database $uid")
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COL + " = $uid LIMIT 1", null)
    }

    fun deletePeople(uid: Int) : Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, ID_COL +"=" + uid, null)
    }

    companion object {
        private val DATABASE_NAME = "ANDROIDKTCLASS"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "qt_table"
        val ID_COL = "id"
        val FIRSTNAME_COL = "firstname"
        val LASTNAME_COL = "lastname"
        val REWARDS_COL = "rewards"
    }
}