
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // ── Retrieve data from Intent extras ──────────────────────────
        // getStringExtra uses the same KEY as putExtra in MainActivity
        val receivedName = intent.getStringExtra("KEY_NAME") ?: "Guest"
        val passwordLength = intent.getIntExtra("KEY_PASSWORD_LENGTH", 0)

        // ── Bind to UI ────────────────────────────────────────────────
        val tvWelcomeName   = findViewById<TextView>(R.id.tvWelcomeName)
        val tvReceivedData  = findViewById<TextView>(R.id.tvReceivedData)
        val btnBack         = findViewById<Button>(R.id.btnBack)

        tvWelcomeName.text  = receivedName
        tvReceivedData.text =
            "👤 Name: $receivedName\n🔑 Password length: $passwordLength characters"

        // ── Back button: close this activity and return to Login ──────
        btnBack.setOnClickListener {
            finish()   // destroys WelcomeActivity and goes back to stack
        }
    }
}
