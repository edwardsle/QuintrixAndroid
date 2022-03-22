package com.example.simpletable

import android.annotation.SuppressLint
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    @SuppressLint("Range")
    var edit_uid: EditText? = null
    var edit_firstname: EditText? = null
    var edit_lastname: EditText? = null
    var edit_rewards: EditText? = null
    var edit_memberid: EditText? = null
    var display_info: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit_uid = findViewById<View>(R.id.uid) as EditText
        edit_firstname = findViewById<View>(R.id.firstname) as EditText
        edit_lastname = findViewById<View>(R.id.lastname) as EditText
        edit_rewards = findViewById<View>(R.id.rewards) as EditText
        edit_memberid = findViewById<View>(R.id.memberid) as EditText
        display_info = findViewById<View>(R.id.display_info) as TextView
    }

    fun addMember(view: View?) {
        val uid = edit_uid!!.text.toString().toInt()
        val firstname = edit_firstname!!.text.toString()
        val lastname= edit_lastname!!.text.toString()
        val rewards = edit_rewards!!.text.toString().toInt()
        val db = DBHelper(this, null)

        db.addPeople(uid, firstname, lastname, rewards)
        Toast.makeText(this, "$uid added to database", Toast.LENGTH_LONG).show()

        edit_uid!!.setText("")
        edit_firstname!!.setText("")
        edit_lastname!!.setText("")
        edit_rewards!!.setText("")
    }

    @SuppressLint("Range")
    fun showMember(view: View?) {
        val uid = edit_memberid!!.text.toString().toInt()
        val db = DBHelper(this, null)
        val cursor: Cursor? = db.getPeople(uid)

        cursor!!.moveToFirst()
        if(cursor.getCount() > 0) {
            val userid = cursor.getString(cursor.getColumnIndex(DBHelper.ID_COL))
            val firstname = cursor.getString(cursor.getColumnIndex(DBHelper.FIRSTNAME_COL))
            val lastname = cursor.getString(cursor.getColumnIndex(DBHelper.LASTNAME_COL))
            val rewards = cursor.getString(cursor.getColumnIndex(DBHelper.REWARDS_COL))

            display_info!!.text = "ID: $userid \nName: $lastname, $firstname \nRewards: $rewards"
        }
        cursor.close()
    }

    fun deleteMember(view: View?) {
        val uid = edit_memberid!!.text.toString().toInt()
        println(uid)

        val db = DBHelper(this, null)
        db.deletePeople(uid)

        display_info!!.text = "$uid has been removed!"
    }
}

