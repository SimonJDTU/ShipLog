package com.johansen.dk.shiplog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.johansen.dk.shiplog.adapters.NoteAdapter
import com.johansen.dk.shiplog.data.Note
import com.johansen.dk.shiplog.data.Trip
import kotlinx.android.synthetic.main.activity_create_trip.*

class CreateTrip : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val trip = Trip("Helge Ask", 15, "Troels")
    private val noteList : MutableList<Note> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_trip)

        endTripBtn.setOnClickListener {if (checkFinishOkay()) finish() else Toast.makeText(this,"yeet",Toast.LENGTH_SHORT).show() }
        addNoteBtn.setOnClickListener{
            noteList.add(Note("test","test","test","test","test"))
            makeNoteList()
        }
        makeNoteList()
    }

    private fun makeNoteList() {
        notes_list.layoutManager = LinearLayoutManager(this)
        notes_list.adapter = NoteAdapter(noteList, this)
    }

    private fun checkFinishOkay() : Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        finish()
    }


    private fun createTrip(){
        db.collection("trips")
            .add(trip)
            .addOnSuccessListener {
                Toast.makeText(applicationContext,"New trip added", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e -> Log.w("TAG","Error adding document", e)
                Toast.makeText(applicationContext,"Couldn't add new trip", Toast.LENGTH_LONG).show()
            }
    }

    override fun onBackPressed() {
        Toast.makeText(this,"KLIK PÅ SLUT TUR KNAPPEN",Toast.LENGTH_SHORT).show()
    }

}
