package com.johansen.dk.shiplog.data

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage


data class Ship(val name : String, private val imageLink : String){

    var image : Uri? = getImage(imageLink)

}

private fun getImage(imageLink : String) : Uri {

    val storageRef =  FirebaseStorage.getInstance().reference
    val pathReference = storageRef.child(imageLink)
    lateinit var image : Uri

    pathReference.downloadUrl.addOnSuccessListener {
        image=it
    }.addOnFailureListener {
        print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
        it.printStackTrace()
    }
    return image
}

