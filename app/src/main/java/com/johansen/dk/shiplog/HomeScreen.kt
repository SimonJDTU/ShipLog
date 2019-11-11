package com.johansen.dk.shiplog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.johansen.dk.shiplog.adapters.TripsAdapter
import com.johansen.dk.shiplog.data.Trip
import kotlinx.android.synthetic.main.activity_home_screen.*
import com.google.firebase.firestore.FirebaseFirestore


class HomeScreen : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        setOnclickListeners()

        //todo:remove
        //insertDummyData()
    }

    private fun createTripList() {
        val trips : MutableList<Trip> = mutableListOf()
        db.collection("trips")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        trips.add(Trip(document.get("shipName").toString(),document.get("date").toString(),
                            document.get("length").toString(),document.get("crewSize").toString(),document.get("tripID").toString()))
                    }
                    trips_list.layoutManager = LinearLayoutManager(this)
                    trips_list.adapter = TripsAdapter(trips, this)
                    loaderAnimation.visibility = View.GONE
                    trips_list.visibility = View.VISIBLE
                } else {
                    trips_list.visibility = View.GONE
                    loaderAnimation.visibility = View.VISIBLE
                    Toast.makeText(applicationContext, "Couldn't read trip", Toast.LENGTH_LONG).show()
                }
            }
    }

    //TODO: make custom dialog to display ships
    private fun customDialog(){
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.customdialog, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("Login Form")

        mBuilder.show()
    }

    private fun setOnclickListeners() {
        home_trip.setOnClickListener{startActivity(Intent(this, CreateTrip::class.java))}
        home_boats.setOnClickListener{customDialog()}
        home_settings.setOnClickListener{startActivity(Intent(this, Settings::class.java))}
    }


    //Todo: Re eval toasts
    private fun writeNewTrip(trip : Trip) {
        db.collection("trips")
            .add(trip)
            .addOnSuccessListener {
                Toast.makeText(applicationContext,"New trip added",Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e -> Log.w("TAG","Error adding document", e)
                Toast.makeText(applicationContext,"Couldn't add new trip",Toast.LENGTH_LONG).show()
            }
    }

    //Todo: Delete when unnecessary
    private fun insertDummyData(){
        for (x in 0..10){
            writeNewTrip(Trip("Helge Ask", (x*2+1).toString(),(x+3).toString(),(x*2+1).toString()))
        }
    }

    //dont want to reinvent the wheel: https://stackoverflow.com/questions/8430805/clicking-the-back-button-twice-to-exit-an-activity
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, getString(R.string.toast_backToLogin), Toast.LENGTH_LONG).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    override fun onResume() {
        super.onResume()
        loaderAnimation.visibility = View.VISIBLE
        createTripList()
    }
}
