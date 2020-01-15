package com.johansen.dk.shiplog.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johansen.dk.shiplog.R
import com.johansen.dk.shiplog.data.Note
import kotlinx.android.synthetic.main.note_recycler_card.view.*
import java.util.*


class NoteAdapter(private val items : MutableList<Note>, private val context: Context) : RecyclerView.Adapter<NoteViewHolder>() {

    // Gets the number of items in list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_recycler_card, parent, false))
        }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val temp = items[position]
        val cal = Calendar.getInstance()
        //holder.time?.text = temp.time
        holder.windSpeed.text = temp.windSpeed
        holder.windDirection.text = temp.windDirection
        holder.sailSpeed.text = temp.shipSpeed
        holder.sailDirection.text = temp.shipDirection
        holder.tripDate.text = cal.time.toString()
    }
}

class NoteViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    //val time = view.note_date
    val sailSpeed = view.note_sailSpeed!!
    val sailDirection = view.note_sailDirection!!
    val windSpeed = view.note_windSpeed!!
    val windDirection = view.note_windDirection!!
    val tripDate = view.note_date
}



