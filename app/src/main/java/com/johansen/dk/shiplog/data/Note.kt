package com.johansen.dk.shiplog.data

data class Note(val time : String = getTime(), val shipSpeed : String, val shipDirection : String, val windSpeed : String, val windDirection : String)

fun getTime(): String {
    return "Not Implemented"
}
