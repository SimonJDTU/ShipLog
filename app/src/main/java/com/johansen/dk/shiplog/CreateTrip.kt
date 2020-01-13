package com.johansen.dk.shiplog

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.johansen.dk.shiplog.adapters.NoteAdapter
import com.johansen.dk.shiplog.data.Note
import com.johansen.dk.shiplog.data.Trip
import kotlinx.android.synthetic.main.activity_create_trip.*

class CreateTrip : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val trip = Trip("Helge Ask", 15, "Troels")
    private val noteList: MutableList<Note> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_trip)

        notes_list.layoutManager = LinearLayoutManager(this)
        notes_list.adapter = NoteAdapter(noteList, this)

        addNoteBtn.setOnClickListener { addNoteToList() }
        endTripBtn.setOnClickListener { if (checkFinishOkay()) finish() }
    }

    private fun addNoteToList() {
        if (note_shipDirection.text.toString() == "" || note_shipSpeed.text.toString() == "" || note_windDirection.text.toString() == "" || note_windSpeed.text.toString() == "") {
            Toast.makeText(this, R.string.toast_fillAllBoxes, Toast.LENGTH_SHORT).show()
        } else {
            noteList.add(
                Note(
                    shipDirection = note_shipDirection.text.toString(),
                    shipSpeed = note_shipSpeed.text.toString() + " km/t",
                    windDirection = note_windDirection.text.toString(),
                    windSpeed = note_windSpeed.text.toString() + " km/t"
                )
            )
            notes_list.adapter!!.notifyDataSetChanged()

            note_shipDirection.setText("")
            note_shipSpeed.setText("")
            note_windDirection.setText("")
            note_windSpeed.setText("")
        }
    }

    private fun checkFinishOkay(): Boolean {
        createTrip()
        return true
    }

    private fun createTrip() {
        db.collection("trips")
            .add(trip)
            .addOnSuccessListener {
                Toast.makeText(applicationContext, R.string.toast_tripAdded, Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
                Toast.makeText(applicationContext, R.string.toast_tripNotAdded, Toast.LENGTH_LONG)
                    .show()
            }
    }

    override fun onBackPressed() {
        Toast.makeText(this, R.string.toast_endTrip, Toast.LENGTH_SHORT).show()
    }

}
