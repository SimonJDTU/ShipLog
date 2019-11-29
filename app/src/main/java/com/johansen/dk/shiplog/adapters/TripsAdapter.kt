package com.johansen.dk.shiplog.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johansen.dk.shiplog.R
import com.johansen.dk.shiplog.data.Trip
import kotlinx.android.synthetic.main.trips_recycler_card.view.*

class TripsAdapter(val items : MutableList<Trip>, val context: Context) : RecyclerView.Adapter<tripViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tripViewHolder {
        return tripViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.trips_recycler_card,
                parent,
                false
            )
        )
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: tripViewHolder, position: Int) {
        val temp = items.get(position)
        //holder?.trip_date?.text = temp.tripStart.toLocalDate().toString()
        holder?.trip_ship?.text = temp.shipName
        holder?.trip_captain?.text = temp.crewCaptain
        holder?.trip_crewsize?.text = temp.crewSize.toString()
    }
}

class tripViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    //val trip_date = view.trip_date
    val trip_ship = view.trip_ship
    val trip_captain = view.trip_captain
    val trip_crewsize = view.trip_crewsize
}