package com.johansen.dk.shiplog

import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.johansen.dk.shiplog.adapters.ShipAdapter
import com.johansen.dk.shiplog.adapters.TripsAdapter
import com.johansen.dk.shiplog.data.Ship
import com.johansen.dk.shiplog.data.Trip
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.activity_ship_overview.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*
import kotlin.collections.ArrayList

class ShipOverview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ship_overview)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val ships = intent.getSerializableExtra("ships") as ArrayList<Ship>

        ship_list.layoutManager = LinearLayoutManager(this)
        val listAdapter = ShipAdapter(ships, this)
        ship_list.adapter = listAdapter

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.no_movement,R.anim.slide_out_down)
    }
}
