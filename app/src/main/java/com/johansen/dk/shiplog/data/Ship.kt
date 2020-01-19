package com.johansen.dk.shiplog.data

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Parcelable
import com.google.firebase.storage.FirebaseStorage
import io.opencensus.resource.Resource
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.io.Serializable


data class Ship(val name: String, val imageLink: Long) : Serializable



