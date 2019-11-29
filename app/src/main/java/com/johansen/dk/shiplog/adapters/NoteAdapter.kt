package com.johansen.dk.shiplog.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johansen.dk.shiplog.R
import com.johansen.dk.shiplog.data.Note
import com.johansen.dk.shiplog.data.Trip
import kotlinx.android.synthetic.main.trips_recycler_card.view.*

class NoteAdapter(private val items : MutableList<Note>, private val context: Context) : RecyclerView.Adapter<NoteViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.note_recycler_card,
                parent,
                false
            )
        )
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val temp = items[position]
        holder.trip_ship?.text = temp.shipSpeed

    }
}

class NoteViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    //val trip_date = view.trip_date
    val trip_ship = view.trip_ship

}