package com.johansen.dk.shiplog

import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.crashlytics.android.Crashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.johansen.dk.shiplog.adapters.TripsAdapter
import com.johansen.dk.shiplog.data.Ship
import com.johansen.dk.shiplog.data.Trip
import kotlinx.android.synthetic.main.activity_home_screen.*


class HomeScreen : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false
    private val db = FirebaseFirestore.getInstance()
    private lateinit var vibe : Vibrator

    //private val mStorageRef = FirebaseStorage.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        setOnclickListeners()

    }

    private fun createTripList() {
        val trips : MutableList<Trip> = mutableListOf()
        db.collection("trips")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        trips.add(Trip(document.get("shipName") as String,
                            document.get("crewSize") as Long,document.get("crewCaptain") as String))
                    }
                    trips_list.layoutManager = LinearLayoutManager(this)
                    trips_list.adapter = TripsAdapter(trips, this)
                    loaderAnimation.visibility = View.GONE
                    trips_list.visibility = View.VISIBLE
                } else {
                    trips_list.visibility = View.GONE
                    loaderAnimation.visibility = View.VISIBLE
                    Toast.makeText(applicationContext, R.string.toast_fetchTripFailed, Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun setOnclickListeners() {
        home_trip.setOnClickListener{
            startActivity(Intent(this, PreTrip::class.java))
            overridePendingTransition(R.anim.slide_in_bottom,R.anim.no_movement)
        }

        home_boats.setOnClickListener{
            startActivity(Intent(this, ShipOverview::class.java))
            overridePendingTransition(R.anim.slide_in_bottom,R.anim.no_movement)
        }

        home_settings.setOnClickListener{
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.dmi.dk/")
            startActivity(openURL)
            overridePendingTransition(R.anim.slide_in_bottom,R.anim.no_movement)
        }
    }

    //Todo: Delete when unnecessary
    private fun insertTripDummyData(){
        for (x in 0..10){
            val trip = Trip("Helge Ask", 15, "Mogens Hansen")
            db.collection("trips")
                .add(trip)
                .addOnSuccessListener {
                    Toast.makeText(applicationContext,"New trip added",Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { e -> Log.w("TAG","Error adding document", e)
                    Toast.makeText(applicationContext,"Couldn't add new trip",Toast.LENGTH_LONG).show()
                }
        }
    }

    //dont want to reinvent the wheel: https://stackoverflow.com/questions/8430805/clicking-the-back-button-twice-to-exit-an-activity
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
        }else{
            vibrate()
            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, getString(R.string.toast_closeApp), Toast.LENGTH_LONG).show()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        }
    }

    override fun onResume() {
        super.onResume()
        loaderAnimation.visibility = View.VISIBLE
        trips_list.visibility = View.GONE
        createTripList()
    }

    private fun vibrate(){
        if (Build.VERSION.SDK_INT >= 26) {
            vibe.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibe.vibrate(200)
        }
    }




}
