package com.johansen.dk.shiplog

import android.content.Context
import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.johansen.dk.shiplog.data.Ship
import kotlinx.android.synthetic.main.activity_pre_trip.*

class PreTrip : AppCompatActivity() {

    private val shipList : List<Ship> = emptyList()
    private var methodSelected : Boolean? = null
    private var doubleBackToExitPressedOnce = false
    private lateinit var vibe : Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_trip)

        vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        setOnclickListeners()

        setShipShowcase(null)

        }

    //TODO: Insert when implemented image data collection from firebase storage
    private fun setShipShowcase(ship : Ship?) {
        if (ship != null) {
            preTrip_shipName.text = ship.name
            //TODO: Set image connected to shipresource
            preTrip_shipSelection.setImageResource(R.mipmap.image2)
        }else{
            preTrip_shipName.text = "HELGE ASK _ TEMP TEXT"
            preTrip_shipSelection.setImageResource(R.mipmap.image2)
        }
    }

    private fun setOnclickListeners() {
        preTrip_startTripBtn.setOnClickListener {
            if (regexCheck()) {
                dialogContinue()
            } else {
                Toast.makeText(this, R.string.toast_insertInfo, Toast.LENGTH_SHORT).show()
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
        if(preTrip_captain.text.toString().trim().isEmpty() || methodSelected==null || preTrip_crewsize.text.toString().trim().isEmpty()){
            return false
        }
        return true
    }

    //dont want to reinvent the wheel: https://stackoverflow.com/questions/8430805/clicking-the-back-button-twice-to-exit-an-activity
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        } else{
            vibrate()
            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, getString(R.string.toast_backToHome), Toast.LENGTH_LONG).show()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        }

    }

    private fun dialogShipchoice(){
        val myItems = listOf("Hello", "World")

        MaterialDialog(this).show {
            listItemsSingleChoice(
                items = myItems,
                initialSelection = 0
            ) { dialog, index, text ->
                //TODO: Select the correct ship
            }
            positiveButton(R.string.dialog_button_positive)
        }
    }

    private fun dialogContinue(){
        MaterialDialog(this).show {
            message(R.string.dialog_preTrip_msg)
            positiveButton(R.string.dialog_button_yes) { dialog ->
                goToCreateActivity()
            }
            negativeButton(R.string.dialog_button_no) { dialog ->
                this.dismiss()
            }
        }
    }

    private fun goToCreateActivity(){
        startActivity(Intent(this, CreateTrip::class.java))
        finish()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.no_movement,R.anim.slide_out_down)
    }

    private fun vibrate(){
        if (Build.VERSION.SDK_INT >= 26) {
            vibe.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibe.vibrate(200)
        }
    }
}
