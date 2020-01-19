package com.johansen.dk.shiplog

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Timestamp
import com.google.gson.JsonParser
import com.johansen.dk.shiplog.adapters.NoteAdapter
import com.johansen.dk.shiplog.adapters.ShipAdapter
import com.johansen.dk.shiplog.data.DisplayTrip
import com.johansen.dk.shiplog.data.Note
import kotlinx.android.synthetic.main.activity_create_trip.*
import kotlinx.android.synthetic.main.activity_ship_overview.*
import kotlinx.android.synthetic.main.activity_view_trip.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.reflect.typeOf

class ViewTrip : AppCompatActivity() {

    private lateinit var tripData : DisplayTrip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_trip)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        tripData = intent.getSerializableExtra("tripData") as DisplayTrip

        setViewsFromData()

        note_list.layoutManager = LinearLayoutManager(this)

        val listAdapter = NoteAdapter(createNotesFromData(), this)
        note_list.adapter = listAdapter

    }

    private fun createNotesFromData() : ArrayList<Note> {
        val notes : ArrayList<Note> = ArrayList()
        for(it in tripData.noteList){
            it as HashMap<String,String>
            notes.add(
                Note(
                    it["noteTime"].toString().toLong(),
                    it["shipSpeed"].toString(),
                    it["shipDirection"].toString(),
                    it["windSpeed"].toString(),
                    it["windDirection"].toString()
                ))
        }
        return notes
    }

    private fun setViewsFromData() {
        val format = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val startTimeStamp = format.format(Date(tripData.tripStartSec))
        val endTimeStamp = format.format(Date(tripData.tripEndSec))
        startDate.text=startTimeStamp.toString()
        endDate.text=endTimeStamp.toString()
        shipName.text=tripData.shipData.name
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.no_movement,R.anim.slide_out_down)
    }
}
