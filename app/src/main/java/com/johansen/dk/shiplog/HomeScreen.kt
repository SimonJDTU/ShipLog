package com.johansen.dk.shiplog

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.*
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.johansen.dk.shiplog.adapters.OnTripClickListener
import com.johansen.dk.shiplog.adapters.TripsAdapter
import com.johansen.dk.shiplog.data.DisplayTrip
import com.johansen.dk.shiplog.data.Note
import com.johansen.dk.shiplog.data.Ship
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlin.collections.ArrayList


class HomeScreen : AppCompatActivity(), OnTripClickListener {
    private var doubleBackToExitPressedOnce = false
    private val db = FirebaseFirestore.getInstance()
    private lateinit var vibe: Vibrator
    private val trips: MutableList<DisplayTrip> = mutableListOf()
    private val ships: MutableList<Ship> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        createShips()

        setOnclickListeners()
    }

    private fun createShips() {
        db.collection("ships")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        ships.add(Ship(document.get("name") as String, document.get("imageLink") as Long))
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        R.string.toast_fetchTripFailed,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun createTripList() {
        db.collection("trips")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {

                        trips.clear()

                        val tempMap : Map<*,*> = document.get("shipData") as Map<*,*>

                        val tempTrip = DisplayTrip(
                            Ship(tempMap["name"].toString(), tempMap["imageLink"].toString().toLong()),
                            document.get("crewSize") as Long,
                            document.get("crewCaptain") as String,
                            document.get("tripStart") as Long,
                            document.get("tripEnd") as Long,
                            document.get("noteList") as ArrayList<Any>,
                            document.get("drivingMethod") as Boolean
                        )

                        trips.add(tempTrip)

                    }
                    trips_list.layoutManager = LinearLayoutManager(this)
                    val listAdapter = TripsAdapter(trips, this, this)
                    trips_list.adapter = listAdapter
                    loaderAnimation.visibility = View.GONE
                    trips_list.visibility = View.VISIBLE
                } else {
                    trips_list.visibility = View.GONE
                    loaderAnimation.visibility = View.VISIBLE
                    Toast.makeText(
                        applicationContext,
                        R.string.toast_fetchTripFailed,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun setOnclickListeners() {
        home_trip.setOnClickListener {
            if(ships.isEmpty()){
                Toast.makeText(this,R.string.toast_waitTilFetchFinish,Toast.LENGTH_SHORT).show()
            }else{
            val intent = Intent(this, PreTrip::class.java)
            intent.putExtra("ships", ArrayList(ships))
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.no_movement)
            }
        }

        home_boats.setOnClickListener {
            val intent = Intent(this, ShipOverview::class.java)
            intent.putExtra("ships", ArrayList(ships))
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.no_movement)
        }

        home_settings.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.dmi.dk/")
            startActivity(openURL)
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.no_movement)
        }
    }

    //dont want to reinvent the wheel: https://stackoverflow.com/questions/8430805/clicking-the-back-button-twice-to-exit-an-activity
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
        } else {
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

    private fun vibrate() {
        if (Build.VERSION.SDK_INT >= 26) {
            vibe.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibe.vibrate(200)
        }
    }

    override fun onItemClick(item: DisplayTrip, position: Int) {
        val intent = Intent(this, ViewTrip::class.java)
        intent.putExtra("tripData", trips[position])
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.no_movement)
    }
}
