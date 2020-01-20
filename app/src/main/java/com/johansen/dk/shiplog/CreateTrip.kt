package com.johansen.dk.shiplog

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.johansen.dk.shiplog.adapters.NoteAdapter
import com.johansen.dk.shiplog.data.Note
import com.johansen.dk.shiplog.data.Ship
import com.johansen.dk.shiplog.data.Trip
import kotlinx.android.synthetic.main.activity_create_trip.*
import java.util.*


class CreateTrip : AppCompatActivity() {

    private val calInstanceStart = Calendar.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val noteList: MutableList<Note> = mutableListOf()
    private lateinit var vibe: Vibrator
    private lateinit var inputManager: InputMethodManager
    private lateinit var trip: Trip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_trip)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager


        trip = Trip(
            intent.getSerializableExtra("ship") as Ship,
            intent.getSerializableExtra("crewSize") as String,
            intent.getSerializableExtra("captain") as String,
            calInstanceStart.time.time,
            intent.getSerializableExtra("drivingMethod") as Boolean
        )

        notes_list.layoutManager = LinearLayoutManager(this)
        notes_list.adapter = NoteAdapter(noteList, this)

        setOnclickListener()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.no_movement, R.anim.slide_out_down)
    }

    private fun setOnclickListener() {
        addNoteBtn.setOnClickListener { addNoteToList() }

        create_endTrip.setOnClickListener {
            if (checkFinishOkay()) {
                MaterialDialog(this).show {
                    message(R.string.dialog_endTrip_msg)
                    positiveButton(R.string.dialog_button_yes) { dialog ->
                        createTrip()
                        finish()
                    }
                    negativeButton(R.string.dialog_button_no) { dialog ->
                        dismiss()
                    }
                }
            } else {
                Toast.makeText(this, R.string.toast_emptyAllBoxes, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addNoteToList() {
        if (note_shipDirection.text.toString().isNotEmpty()
            && note_shipSpeed.text.toString().isNotEmpty()
            && note_windDirection.text.toString().isNotEmpty()
            && note_windSpeed.text.toString().isNotEmpty()
        ) {
            inputManager.hideSoftInputFromWindow(
                currentFocus.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
            val cal = Calendar.getInstance()
            noteList.add(
                Note(
                    noteTime = cal.time.time,
                    shipDirection = note_shipDirection.text.toString(),
                    shipSpeed = note_shipSpeed.text.toString(),
                    windDirection = note_windDirection.text.toString(),
                    windSpeed = note_windSpeed.text.toString()
                )
            )
            notes_list.adapter!!.notifyDataSetChanged()

            note_shipDirection.setText("")
            note_shipSpeed.setText("")
            note_windDirection.setText("")
            note_windSpeed.setText("")
        } else {
            Toast.makeText(this, R.string.toast_fillAllBoxes, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkFinishOkay(): Boolean {
        if (note_shipDirection.text.toString().isEmpty()
            && note_shipSpeed.text.isEmpty()
            && note_windDirection.text.isEmpty()
            && note_windSpeed.text.isEmpty()
        ) {
            return true
        }
        return false
    }

    private fun createTrip() {
        trip.tripEnd = Calendar.getInstance().time.time
        trip.noteList = noteList
        db.collection("trips")
            .add(trip)
            .addOnSuccessListener {
                Toast.makeText(applicationContext, R.string.toast_tripAdded, Toast.LENGTH_LONG)
                    .show()
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
                Toast.makeText(applicationContext, R.string.toast_tripNotAdded, Toast.LENGTH_LONG)
                    .show()
            }
    }

    override fun onBackPressed() {
        vibrate()
        Toast.makeText(this, R.string.toast_endTrip, Toast.LENGTH_SHORT).show()
    }

    private fun vibrate() {
        if (Build.VERSION.SDK_INT >= 26) {
            vibe.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibe.vibrate(200)
        }
    }

}
