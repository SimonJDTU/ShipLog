package com.johansen.dk.shiplog

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_disclaimer.*

class Disclaimer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disclaimer)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    }
}
