package com.johansen.dk.shiplog.data

import java.io.Serializable
import kotlin.collections.HashMap


class DisplayTrip(val shipData: Ship, val crewSize: Long, val crewCaptain: String, val tripStartSec: Long, val tripEndSec: Long, val noteList: ArrayList<Any>, val drivingMethod: Boolean) : Serializable


