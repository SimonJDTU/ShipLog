package com.johansen.dk.shiplog.managers

class StateManager private constructor() {

    private object HOLDER {
        val INSTANCE = StateManager()
    }

    companion object {
        val instance: StateManager by lazy { HOLDER.INSTANCE }
    }
}