package com.example.registrationform

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import kotlin.invoke


class AirplaneModeReceiver : BroadcastReceiver() {

    var onAirplaneModeChanged: ((Boolean) -> Unit)? = null

    override fun onReceive(context: Context, intent: Intent) {

        // Get the state — true = ON, false = OFF
        val isAirplaneModeOn = intent.getBooleanExtra("state", false)

        // Show Toast message
        if (isAirplaneModeOn) {
            Toast.makeText(context, "Airplane Mode ON", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Airplane Mode OFF", Toast.LENGTH_LONG).show()
        }

        // Notify Activity to update the status TextView
        onAirplaneModeChanged?.invoke(isAirplaneModeOn)
    }
}

