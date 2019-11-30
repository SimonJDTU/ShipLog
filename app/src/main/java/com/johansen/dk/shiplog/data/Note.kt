package com.johansen.dk.shiplog.data

data class Note(val time : String = getTime()){
    lateinit var shipSpeed : String
    lateinit var shipDirection : String
    lateinit var windSpeed : String
    lateinit var windDirection : String
}
fun getTime(): String {
    return "Not Implemented"
}
