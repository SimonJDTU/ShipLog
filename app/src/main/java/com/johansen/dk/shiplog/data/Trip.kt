package com.johansen.dk.shiplog.data

import java.util.*

import kotlin.random.Random

class Trip(var shipName : String, var crewSize : Long, var crewCaptain : String){

    private lateinit var testArray : MutableList<Note>
    private lateinit var tripID : String
    private lateinit var tripStart : String
    private lateinit var tripEnd : String


    fun setTripID(ID: String){
        tripID=ID
    }

}

fun randomGenerator(): String {
    val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    return (1..10)
        .map { i -> Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
}


