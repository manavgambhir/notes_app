package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_add.view.*
import kotlinx.android.synthetic.main.list_item.view.*

class notesAdapter(val notesList: List<reqNotes>): RecyclerView.Adapter<notesAdapter.notesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        val listData = LayoutInflater.from(parent.context).inflate(R.layout.list_item , parent,false)
        return notesViewHolder(listData)
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        holder.bind(notesList[position])
    }

    override fun getItemCount(): Int = notesList.size

    inner class notesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(note:reqNotes) = with(itemView){
            tvTitle.text = note.title
            tvContent.text = note.subTitle
        }
    }
}