package com.johansen.dk.shiplog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.johansen.dk.shiplog.data.Ship
import kotlinx.android.synthetic.main.activity_pre_trip.*

class PreTrip : AppCompatActivity() {

    private val shipList : List<Ship> = emptyList()
    private var methodSelected : Boolean? = null
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_trip)

        setOnclickListeners()

        setShipShowcase()


        }

    //TODO: Insert when implemented image data collection from firebase storage
    private fun setShipShowcase() {
        preTrip_shipSelection.setImageResource(R.mipmap.image2)
        preTrip_shipName.text = "Helge Ask _ TEMPORARY"
    }

    private fun setOnclickListeners() {
        preTrip_startTripBtn.setOnClickListener {
            if (regexCheck()) {
                dialogContinue()
            } else {
                //TODO: make text adaptive to translation
                Toast.makeText(this, "Udfyld venligst alle elementer", Toast.LENGTH_SHORT).show()
            }
        }

        //if motor is selected value of boolean methodSelected = false
        //if oars is selected value of boolean methodSelected = true
        preTrip_motor.setOnClickListener {
            methodSelected=false
            preTrip_motor.setBackgroundResource(R.drawable.button_circle_blue)
            preTrip_oars.setBackgroundResource(R.drawable.button_circle_gray)
        }

        preTrip_oars.setOnClickListener {
            methodSelected=true
            preTrip_oars.setBackgroundResource(R.drawable.button_circle_blue)
            preTrip_motor.setBackgroundResource(R.drawable.button_circle_gray)
        }

        preTrip_shipSelection.setOnClickListener{dialogShipchoice()}

}

    private fun regexCheck(): Boolean {
        if(preTrip_captain.text==null || methodSelected==null){
            return true
        }
        return false
    }

    //dont want to reinvent the wheel: https://stackoverflow.com/questions/8430805/clicking-the-back-button-twice-to-exit-an-activity
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, getString(R.string.toast_backToHome), Toast.LENGTH_LONG).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun dialogShipchoice(){
        val myItems = listOf("Hello", "World")

        MaterialDialog(this).show {
            listItemsSingleChoice(
                items = myItems,
                initialSelection = 1
            ) { dialog, index, text ->
                //TODO: Select the correct ship
            }
            positiveButton(R.string.dialog_button_positive)
        }
    }

    private fun dialogContinue(){
        val myItems = listOf("Hello", "World")

        MaterialDialog(this).show {
            listItemsSingleChoice(items = myItems)
        }
    }
}
