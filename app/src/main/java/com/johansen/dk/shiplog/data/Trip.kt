package com.johansen.dk.shiplog.data

import kotlin.random.Random

data class Trip(var shipName : String, var date : String, var length : String, var crewSize : String, val tripID : String = randomGenerator() )

fun randomGenerator(): String {
    val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    return (1..10)
        .map { i -> Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
}
