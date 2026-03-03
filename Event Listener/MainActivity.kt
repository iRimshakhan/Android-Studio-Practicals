import android.annotation.SuppressLint
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    // Holds all event messages — appended one by one
    private var log = ""

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etInput  = findViewById<EditText>(R.id.etInput)
        val imgGrid  = findViewById<ImageView>(R.id.imgGrid)
        val btn      = findViewById<Button>(R.id.btn)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val scroll   = findViewById<ScrollView>(R.id.scrollView)

        // ── Helper: append message and scroll to bottom ───────────────
        fun addLog(message: String) {
            log += "$message\n"
            tvResult.text = log
            // Auto scroll to bottom so latest message is visible
            scroll.post { scroll.fullScroll(ScrollView.FOCUS_DOWN) }
        }

        btn.setOnClickListener {
            addLog("You clicked on Button")
        }

        btn.setOnLongClickListener {
            addLog("You long clicked on Button")
            true   // consume event so onClick doesn't also fire
        }


        imgGrid.setOnTouchListener { _, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN         -> addLog("ACTION DOWN")
                MotionEvent.ACTION_UP           -> addLog("ACTION UP")
                MotionEvent.ACTION_POINTER_DOWN -> addLog("ACTION POINTER DOWN")
            }
            true   // return true so touch events keep firing
        }

        etInput.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN &&
                keyCode == KeyEvent.KEYCODE_ENTER) {
                val typed = etInput.text.toString().trim()
                addLog("Enter pressed: $typed")
                true
            } else {
                false
            }
        }
    }
}
