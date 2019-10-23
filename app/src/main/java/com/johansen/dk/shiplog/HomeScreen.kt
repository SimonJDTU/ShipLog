package com.johansen.dk.shiplog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.johansen.dk.shiplog.Adapters.TripsAdapter
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {


    private val trips: ArrayList<String> = ArrayList()

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
        trips.add("1")
        trips.add("2")
        trips.add("3")
        trips.add("4")
        trips.add("5")
        trips.add("6")
    }

    private fun setOnclickListeners() {
        //TODO: implement
        //home_trip.setOnClickListener{startActivity(Intent(this, ShipOverview::class.java))}
        home_trip.setOnClickListener{Toast.makeText(this@HomeScreen,"NOT YET IMPLEMENTED",Toast.LENGTH_LONG).show()}
        home_settings.setOnClickListener{startActivity(Intent(this, Settings::class.java))}
        //
    }
}
