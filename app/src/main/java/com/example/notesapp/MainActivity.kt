package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val notesList = mutableListOf<reqNotes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewAdapter = notesAdapter(notesList)
        rvNote.layoutManager = LinearLayoutManager(this)
        rvNote.adapter = recyclerViewAdapter

        val dataFire = FirebaseDatabase.getInstance().reference
//        val dataReference = dataRefClass.dataRef
        dataFire.child("notes").addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val data = snapshot.getValue(reqNotes::class.java)
                if (data != null) {
//                    notesList.add(data)
                    notesList.add(0, data)
                    recyclerViewAdapter.notifyDataSetChanged()
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                //Nothing
            }
            override fun onChildRemoved(snapshot: DataSnapshot) {
                //Nothing
            }
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                //Nothing
            }
            override fun onCancelled(error: DatabaseError) {
                //Nothing
            }
        })

        fab.setOnClickListener {
            val intent = Intent(this, Add_activity::class.java)
            startActivity(intent)
        }
    }
}