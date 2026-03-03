import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val backCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Back was pressed — show the exit confirmation dialog
                showExitDialog()
            }
        }

        // Register our callback with the dispatcher
        onBackPressedDispatcher.addCallback(this, backCallback)
    }

    // ── Build and show the AlertDialog ────────────────────────────────
    private fun showExitDialog() {

        AlertDialog.Builder(this)

            // Dialog title shown at the top
            .setTitle("Exit App")

            // Dialog message / question for the user
            .setMessage("Are you sure you want to exit the application?")

            // Optional icon inside the dialog title area
            .setIcon(android.R.drawable.ic_dialog_alert)

            // ── POSITIVE button → Exit ─────────────────────────────
            // This is the "Yes / Confirm" action
            .setPositiveButton("Yes, Exit") { dialog, _ ->
                dialog.dismiss()   // close the dialog first
                Toast.makeText(
                    this,
                    "Goodbye! 👋",
                    Toast.LENGTH_SHORT
                ).show()
                finish()           // then close the Activity (exits app)
            }

            // ── NEGATIVE button → Stay ─────────────────────────────
            // This is the "No / Cancel" action
            .setNegativeButton("No, Stay") { dialog, _ ->
                dialog.dismiss()   // simply close the dialog — app stays open
                Toast.makeText(
                    this,
                    "Welcome back! 😊",
                    Toast.LENGTH_SHORT
                ).show()
            }

            // Prevent dialog from closing when user taps outside it
            // Forces the user to make a deliberate choice
            .setCancelable(false)

            // Build and immediately show the dialog
            .show()
    }
}
