package com.johansen.dk.shiplog.data

import kotlin.properties.Delegates

data class Trip(var shipData: Ship, var crewSize: String, var crewCaptain: String, val tripStart: Long, val drivingMethod : Boolean){

    lateinit var noteList : MutableList<Note>
    var tripEnd by Delegates.notNull<Long>()

}

