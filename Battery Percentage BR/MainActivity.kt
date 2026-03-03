import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declare receiver as a property so we can unregister it later
    private lateinit var batteryReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvBattery  = findViewById<TextView>(R.id.tvBattery)
        val tvCharging = findViewById<TextView>(R.id.tvCharging)

        // Define the BroadcastReceiver inline
        batteryReceiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {

                // Get battery level and scale from intent extras
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)

                // Calculate percentage
                val percentage = (level * 100 / scale)

                // Get charging status
                val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
                val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                        status == BatteryManager.BATTERY_STATUS_FULL

                // Update TextViews on screen
                tvBattery.text  = "Battery Level: $percentage%"
                tvCharging.text = if (isCharging) "Status: Charging ⚡"
                else            "Status: Not Charging"

                // Show Toast — matches exam requirement
                Toast.makeText(context,
                    "Battery Level: $percentage%",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Register receiver when activity is visible
    override fun onResume() {
        super.onResume()
        // ACTION_BATTERY_CHANGED must be registered in code — not Manifest
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryReceiver, filter)
    }

    // Unregister receiver when activity is not visible — prevents memory leak
    override fun onPause() {
        super.onPause()
        unregisterReceiver(batteryReceiver)
    }
}
