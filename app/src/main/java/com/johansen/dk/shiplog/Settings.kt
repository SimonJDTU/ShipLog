package com.johansen.dk.shiplog

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_settings.*

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        SetOnclickListeners()

    }

    private fun SetOnclickListeners() {

        disclaimer_btn.setOnClickListener {startActivity(Intent(this, Disclaimer::class.java)) }

        weather_btn.setOnClickListener{
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.dmi.dk/")
            startActivity(openURL)
        }
    }

}
