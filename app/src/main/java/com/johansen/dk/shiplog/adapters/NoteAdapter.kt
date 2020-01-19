package com.johansen.dk.shiplog.adapters

import android.content.Context
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
        holder.windSpeed.text = temp.windSpeed + " " + context.resources.getString(R.string.windSpeedHint)
        holder.windDirection.text = temp.windDirection + " " + context.resources.getString(R.string.directionHint)
        holder.sailSpeed.text = temp.shipSpeed + " " + context.resources.getString(R.string.sailSpeedHint)
        holder.sailDirection.text = temp.shipDirection + " " + context.resources.getString(R.string.directionHint)
        holder.tripDate.text = Date(temp.noteTime).toString()
        holder.tripCounter.text = "#"+(position+1).toString()
    }
}

class NoteViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val sailSpeed = view.note_sailSpeed!!
    val sailDirection = view.note_sailDirection!!
    val windSpeed = view.note_windSpeed!!
    val windDirection = view.note_windDirection!!
    val tripDate = view.note_date
    val tripCounter = view.note_counter
}



