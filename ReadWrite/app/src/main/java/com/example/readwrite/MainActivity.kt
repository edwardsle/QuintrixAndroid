package com.example.readwrite

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import org.w3c.dom.Text
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private val EXTERNAL_STORAGE_PERMISSION_CODE = 23
    lateinit var shared : SharedPreferences
    var editText: EditText? = null
    // var editPrefText: EditText? = null
    // var editPrefView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shared = getSharedPreferences("Test", Context.MODE_PRIVATE)
        val savePrefButton = findViewById<Button>(R.id.savePref)
        val showPrefButton = findViewById<Button>(R.id.viewPref)
        val editPrefText = findViewById<EditText>(R.id.edt)
        val editPrefView = findViewById<TextView>(R.id.txt)

        savePrefButton.setOnClickListener {
            val edit = shared.edit()
            edit.putString("txt", editPrefText.text.toString())
            edit.apply()
        }

        showPrefButton.setOnClickListener {
            editPrefView.text = shared.getString("txt", "No imported")
        }

        editText = findViewById<View>(R.id.editText_data) as EditText
    }

    fun savePublicly(view: View?) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            EXTERNAL_STORAGE_PERMISSION_CODE
        )

        val editTextData = editText!!.text.toString()
        val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(folder, "QuintrixTrainingPublicFile.txt")
        writeTextData(file, editTextData)
        editText!!.setText("")
    }

    fun savePrivately(view: View?) {
        val editTextData = editText!!.text.toString()
        val folder = getExternalFilesDir("AndroidKotlinTraining")
        val file = File(folder, "QuintrixTrainingPrivateFile.txt")
        writeTextData(file, editTextData)
        editText!!.setText("")
    }

    fun viewInformation(view: View?) {
        val intent = Intent(
            this@MainActivity,
            ViewInformationActivity::class.java
        )
        startActivity(intent)
    }

    private fun writeTextData(file: File, data: String) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(data.toByteArray())
            Toast.makeText(this,
            "Done" + file.absolutePath,
            Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this,
            e.stackTraceToString(),
            Toast.LENGTH_LONG).show()
        } finally {
            if(fileOutputStream !=null) {
                try {
                    fileOutputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}