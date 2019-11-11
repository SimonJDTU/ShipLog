package com.johansen.dk.shiplog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.johansen.dk.shiplog.data.Trip
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CreateTrip : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val trip = Trip("Helge Ask", "15", LocalDateTime.now())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_trip)


    }

    private fun createTrip(){
        db.collection("trips")
            .add(trip)
            .addOnSuccessListener {
                Toast.makeText(applicationContext,"New trip added", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e -> Log.w("TAG","Error adding document", e)
                Toast.makeText(applicationContext,"Couldn't add new trip", Toast.LENGTH_LONG).show()
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        trip.endTrip(LocalDateTime.now())
        createTrip()
    }

}
