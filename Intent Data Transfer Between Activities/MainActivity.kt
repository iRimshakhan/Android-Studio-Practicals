
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName     = findViewById<EditText>(R.id.etName)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin   = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val name     = etName.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // ── Validation ───────────────────────────────────────────
            when {
                name.isEmpty() -> {
                    etName.error = "Name is required"
                    etName.requestFocus()
                }
                password.isEmpty() -> {
                    etPassword.error = "Password is required"
                    etPassword.requestFocus()
                }
                password.length < 4 -> {
                    etPassword.error = "Minimum 4 characters"
                    etPassword.requestFocus()
                }
                else -> {
                    // ── Create explicit Intent to WelcomeActivity ────
                    val intent = Intent(this, WelcomeActivity::class.java)

                    // Attach data as key-value "extras"
                    // KEY_NAME is used to retrieve the value on the other side
                    intent.putExtra("KEY_NAME", name)
                    intent.putExtra("KEY_PASSWORD_LENGTH", password.length) // never pass raw passwords!

                    Toast.makeText(this, "Logging in as $name...", Toast.LENGTH_SHORT).show()
                    startActivity(intent)   // launch WelcomeActivity
                }
            }
        }
    }
}
