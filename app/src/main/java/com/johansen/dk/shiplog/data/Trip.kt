package com.johansen.dk.shiplog.data

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

data class Trip(var shipName : String, var crewSize : String, val tripStart : LocalDateTime, val tripID : String = randomGenerator()){

    private var tripStartString = formatTimeToString(tripStart)
    private var tripEndString : String = ""
    private var tripEnd : LocalDateTime? = null
    private var tripLength : Int? = null
        get() = field
    private var testArray : Array<String>? = null

    fun addEvent(){

    }

    fun endTrip(endTime : LocalDateTime){
        tripLength = tripEnd!!.minute-endTime.minute
        tripEndString = formatTimeToString(endTime)
    }

    fun formatTimeToString(current : LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        return current.format(formatter)
    }

}


fun randomGenerator(): String {
    val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    return (1..10)
        .map { i -> Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
}


