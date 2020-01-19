package com.johansen.dk.shiplog.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johansen.dk.shiplog.R
import com.johansen.dk.shiplog.data.DisplayTrip
import kotlinx.android.synthetic.main.trips_recycler_card.view.*
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class TripsAdapter(val items : MutableList<DisplayTrip>, val context: Context, var clickListener: OnTripClickListener) : RecyclerView.Adapter<TripViewHolder>() {

    val format = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        return TripViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.trips_recycler_card,
                parent,
                false
            )
        )
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val temp = items.get(position)
        holder?.trip_ship?.text = temp.shipData.name
        holder?.trip_captain?.text = temp.crewCaptain
        holder?.trip_crewsize?.text = temp.crewSize.toString()
        holder?.trip_date?.text = format.format(Date(temp.tripStartSec))
        holder.initialize(temp,clickListener)
    }
}

class TripViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val trip_ship = view.trip_ship
    val trip_captain = view.trip_captain
    val trip_crewsize = view.trip_crewsize
    val trip_date = view.trip_date

    fun initialize(item : DisplayTrip, action : OnTripClickListener){
        itemView.setOnClickListener {
            action.onItemClick(item,adapterPosition)
        }
    }
}

interface OnTripClickListener{
    fun onItemClick(item : DisplayTrip, position: Int)
}