package com.johansen.dk.shiplog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_ship_overview.*

class ShipOverview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ship_overview)

        chooseBoat_1.setOnClickListener{finish()}
        chooseBoat_2.setOnClickListener{finish()}
        chooseBoat_3.setOnClickListener{finish()}
        chooseBoat_4.setOnClickListener{finish()}

    }
}
