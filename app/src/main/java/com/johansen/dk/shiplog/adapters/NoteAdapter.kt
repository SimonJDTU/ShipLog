package com.johansen.dk.shiplog.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johansen.dk.shiplog.R
import com.johansen.dk.shiplog.data.Note
import kotlinx.android.synthetic.main.btn_recycler_card.view.*
import kotlinx.android.synthetic.main.note_recycler_card.view.*
import kotlinx.android.synthetic.main.trips_recycler_card.view.*


class NoteAdapter(private val items : MutableList<Note>, private val context: Context) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var onItemClick: ((Note) -> Unit)? = null

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size+1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == items.size) 1 else 2
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return when(viewType) {1 -> NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.btn_recycler_card, parent, false))
            else -> NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_recycler_card, parent, false))
        }
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        if(position < items.size){
            val temp = items[position]
            //holder.trip_ship?.text = temp.shipSpeed
            holder.time?.text = temp.time
        } else {
            holder.noteBtn.setOnClickListener {
                onItemClick?.invoke(items[position])
            }
        }
    }

    inner class NoteViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        //val trip_date = view.trip_date
        val trip_ship = view.trip_ship
        val time = view.note_date
        val noteBtn = view.addNoteBtn!!
    }
}

