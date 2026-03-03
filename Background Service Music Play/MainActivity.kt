import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnStop  = findViewById<Button>(R.id.btnStop)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)

        // Start Service button click
        btnStart.setOnClickListener {
            // Create intent pointing to MusicService
            val intent = Intent(this, MusicService::class.java)
            startService(intent)
            tvStatus.text = "Service is running..."
        }

        // Stop Service button click
        btnStop.setOnClickListener {
            val intent = Intent(this, MusicService::class.java)
            stopService(intent)
            tvStatus.text = "Service is stopped"
        }
    }
}
