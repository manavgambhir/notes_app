package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add.*

class Add_activity : AppCompatActivity() {

    val dataRef = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        // Retrieve the title and content from the intent extras
        val title = intent.getStringExtra("title") ?: ""
        val content = intent.getStringExtra("content") ?: ""

        // Set the retrieved data in your EditText fields for editing
        heading.setText(title)
        contentTv.setText(content)

        saveBtn.setOnClickListener{
            val title = heading.text.toString()
            val content = contentTv.text.toString()

            val note = reqNotes(title, content)
            dataRefClass.dataRef = dataRef

            val newNode = dataRef.child("notes").push()
            newNode.setValue(note)

            heading.text?.clear()
            contentTv.text?.clear()
            onBackPressed()
        }
    }
}