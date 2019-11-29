package com.johansen.dk.shiplog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_settings.*

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        InsertLanguages()

    }

    private fun SetOnclickListeners() {
    }

    private fun InsertLanguages() {
        //TODO:remove dummy data + implement correct handling of language choice from spinner
        val items = arrayOf("This isn't implemented", "DK", "ENG", "GER")
        spinner1.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
    }
}
