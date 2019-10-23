package com.johansen.dk.shiplog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.johansen.dk.shiplog.Adapters.TripsAdapter
import kotlinx.android.synthetic.main.activity_home_screen.*
import com.johansen.dk.shiplog.data.Trip

class HomeScreen : AppCompatActivity() {
    private val trips: ArrayList<Trip> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        setOnclickListeners()

        addTrips()
        trips_list.layoutManager = LinearLayoutManager(this)
        trips_list.adapter = TripsAdapter(trips, this)

    }

    //TODO:remove dummy data
    private fun addTrips() {
        trips.add(Trip("Helge Ask", "12 Dec", 120, 10))
        trips.add(Trip("Helge Ask", "13 Dec", 121, 11))
        trips.add(Trip("Helge Ask", "14 Dec", 122, 12))
        trips.add(Trip("Helge Ask", "15 Dec", 123, 13))
        trips.add(Trip("Helge Ask", "16 Dec", 124, 14))
        trips.add(Trip("Helge Ask", "17 Dec", 125, 15))
        trips.add(Trip("Helge Ask", "18 Dec", 126, 16))
        trips.add(Trip("Helge Ask", "19 Dec", 120, 10))
        trips.add(Trip("Helge Ask", "20 Dec", 121, 11))
        trips.add(Trip("Helge Ask", "21 Dec", 122, 12))
        trips.add(Trip("Helge Ask", "22 Dec", 123, 13))
        trips.add(Trip("Helge Ask", "23 Dec", 124, 14))
        trips.add(Trip("Helge Ask", "24 Dec", 125, 15))
        trips.add(Trip("Helge Ask", "25 Dec", 126, 16))
        trips.add(Trip("Helge Ask", "26 Dec", 120, 10))
        trips.add(Trip("Helge Ask", "27 Dec", 121, 11))
        trips.add(Trip("Helge Ask", "28 Dec", 122, 12))
        trips.add(Trip("Helge Ask", "29 Dec", 123, 13))
        trips.add(Trip("Helge Ask", "30 Dec", 124, 14))
        trips.add(Trip("Helge Ask", "31 Dec", 125, 15))
        trips.add(Trip("Helge Ask", "01 Jan", 126, 16))
    }

    private fun setOnclickListeners() {
        //TODO: implement
        //home_trip.setOnClickListener{startActivity(Intent(this, ShipOverview::class.java))}
        home_trip.setOnClickListener{Toast.makeText(this@HomeScreen,"NOT YET IMPLEMENTED",Toast.LENGTH_LONG).show()}
        home_settings.setOnClickListener{startActivity(Intent(this, Settings::class.java))}
        //
    }
}
