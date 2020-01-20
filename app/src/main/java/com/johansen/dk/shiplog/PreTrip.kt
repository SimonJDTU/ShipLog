package com.johansen.dk.shiplog

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.johansen.dk.shiplog.data.Ship
import kotlinx.android.synthetic.main.activity_pre_trip.*

class PreTrip : AppCompatActivity() {

    private var drivingMethod: Boolean? = null
    private var doubleBackToExitPressedOnce = false
    private lateinit var vibe: Vibrator
    private lateinit var ships: MutableList<Ship>
    private var chosenShip : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_trip)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        setOnclickListeners()

        ships = intent.getSerializableExtra("ships") as ArrayList<Ship>

        setShipShowcase(chosenShip)
    }

    //TODO: Insert when implemented image data collection from firebase storage
    private fun setShipShowcase(value : Int) {
        preTrip_shipName.text = ships[value].name
        preTrip_shipImage.setImageResource(ships[value].imageLink.toInt())
    }

    private fun setOnclickListeners() {
        preTrip_startTripBtn.setOnClickListener {
            if (regexCheck()) {
                MaterialDialog(this).show {
                    message(R.string.dialog_preTrip_msg)
                    positiveButton(R.string.dialog_button_yes) { dialog ->
                        goToCreateActivity()
                    }
                    negativeButton(R.string.dialog_button_no) { dialog ->
                        this.dismiss()
                    }
                }
            } else {
                Toast.makeText(this, R.string.toast_insertInfo, Toast.LENGTH_SHORT).show()
            }
        }

        //if motor is selected value of boolean methodSelected = false
        //if oars is selected value of boolean methodSelected = true
        preTrip_motor.setOnClickListener {
            drivingMethod = false
            preTrip_motor.setBackgroundResource(R.drawable.button_circle_blue)
            preTrip_oars.setBackgroundResource(R.drawable.button_circle_gray)
        }

        preTrip_oars.setOnClickListener {
            drivingMethod = true
            preTrip_oars.setBackgroundResource(R.drawable.button_circle_blue)
            preTrip_motor.setBackgroundResource(R.drawable.button_circle_gray)
        }

        preTrip_shipImage.setOnClickListener { dialogShipchoice() }

    }

    private fun regexCheck(): Boolean {
        if (preTrip_captain.text.toString().trim().isEmpty() || drivingMethod == null || preTrip_crewsize.text.toString().trim().isEmpty()) {
            return false
        }
        return true
    }

    //dont want to reinvent the wheel: https://stackoverflow.com/questions/8430805/clicking-the-back-button-twice-to-exit-an-activity
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        } else {
            vibrate()
            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, getString(R.string.toast_backToHome), Toast.LENGTH_LONG).show()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        }

    }

    private fun dialogShipchoice() {
        val myItems : MutableList<String> = mutableListOf()
        for(it in ships){
            myItems.add(it.name)
        }

        MaterialDialog(this).show {
            listItemsSingleChoice(
                items = myItems,
                initialSelection = chosenShip
            ) { dialog, index, text ->
                chosenShip=index
                setShipShowcase(index)
            }
            positiveButton(R.string.dialog_button_positive)
        }
    }


    private fun goToCreateActivity() {
        val intent = Intent(this, CreateTrip::class.java)
        intent.putExtra("ship", ships[chosenShip])
        intent.putExtra("crewSize", preTrip_crewsize.text.toString())
        intent.putExtra("captain", preTrip_captain.text.toString())
        intent.putExtra("drivingMethod", drivingMethod)
        startActivity(intent)
        finish()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.no_movement, R.anim.slide_out_down)
    }

    private fun vibrate() {
        if (Build.VERSION.SDK_INT >= 26) {
            vibe.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibe.vibrate(200)
        }
    }
}
