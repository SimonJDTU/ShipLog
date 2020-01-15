package com.johansen.dk.shiplog

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_text.*
import java.net.URL
import java.util.*
import kotlin.concurrent.thread


class Text : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)

        val date = Date()
        val cal = Calendar.getInstance()


        cal.time = date
        cal.add(Calendar.MONTH, 1)
        cal.timeInMillis


    }
}
