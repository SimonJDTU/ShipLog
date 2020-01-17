package com.johansen.dk.shiplog.data

import android.os.Parcelable
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ship(val name: String, private val imageLink: String) : Parcelable {

    lateinit var image: ByteArray

    init {
        val storageRef = FirebaseStorage.getInstance().reference
        val pathReference = storageRef.child(imageLink)
        val ONE_MEGABYTE: Long = 1024 * 1024
        pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            image = it.clone()
        }.addOnFailureListener {
            it.printStackTrace()
        }
    }
}



