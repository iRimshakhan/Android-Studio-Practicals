import android.content.IntentFilter
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Create receiver instance
    private val airplaneModeReceiver = AirplaneModeReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvStatus = findViewById<TextView>(R.id.tvStatus)

        // Check current airplane mode state when app opens
        val isOnAtStart = Settings.Global.getInt(
            contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0
        ) != 0

        // Set initial status on screen
        updateStatus(tvStatus, isOnAtStart)

        // When receiver detects a change — update the TextView live
        airplaneModeReceiver.onAirplaneModeChanged = { isOn ->
            updateStatus(tvStatus, isOn)
        }
    }

    // Register receiver in onResume so it is always active when app is visible
    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(airplaneModeReceiver, filter)
    }

    // Unregister in onPause to prevent memory leak
    override fun onPause() {
        super.onPause()
        unregisterReceiver(airplaneModeReceiver)
    }

    // Helper function to update status text and color
    private fun updateStatus(tvStatus: TextView, isOn: Boolean) {
        if (isOn) {
            tvStatus.text      = "✈️  Airplane Mode: ON"
            tvStatus.setTextColor(android.graphics.Color.parseColor("#D32F2F")) // red
        } else {
            tvStatus.text      = "📶  Airplane Mode: OFF"
            tvStatus.setTextColor(android.graphics.Color.parseColor("#2E7D32")) // green
        }
    }
}
