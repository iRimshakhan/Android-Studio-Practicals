import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // Hardcoded valid credentials for demo
    private val VALID_USERNAME = "Rimsha Khan"
    private val VALID_PASSWORD = "alleyesonus"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin   = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // ── Validation ────────────────────────────────────────────
            when {
                // Check empty fields first
                username.isEmpty() -> {
                    etUsername.error = "Username is required"
                    etUsername.requestFocus()
                }
                password.isEmpty() -> {
                    etPassword.error = "Password is required"
                    etPassword.requestFocus()
                }

                // Check credentials
                username == VALID_USERNAME && password == VALID_PASSWORD -> {
                    Toast.makeText(this, "✅ Login Successful! Welcome, $username", Toast.LENGTH_LONG).show()
                }
                else -> {
                    Toast.makeText(this, "❌ Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
