package com.johansen.dk.shiplog.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johansen.dk.shiplog.R
import com.johansen.dk.shiplog.data.Trip
import kotlinx.android.synthetic.main.trips_recycler_card.view.*

class TripsAdapter(val items : ArrayList<Trip>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.trips_recycler_card, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val temp = items.get(position)
        holder?.trip_date?.text = temp.date
        holder?.trip_ship?.text = holder?.trip_ship?.text.toString() + temp.shipname
        holder?.trip_fulltime?.text = holder?.trip_fulltime?.text.toString() + temp.length.toString()
        holder?.trip_crewsize?.text = holder?.trip_crewsize?.text.toString() + temp.crewSize.toString()
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val trip_date = view.trip_date
    val trip_ship = view.trip_ship
    val trip_fulltime = view.trip_fulltime
    val trip_crewsize = view.trip_crewsize
}