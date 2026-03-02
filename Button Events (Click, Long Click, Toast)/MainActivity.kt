import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnClick = findViewById<Button>(R.id.btnClick)
        val btnLongClick = findViewById<Button>(R.id.btnLongClick)

        // Click Event
        btnClick.setOnClickListener {
            Toast.makeText(this, "Button Clicked!", Toast.LENGTH_SHORT).show()
        }

        // Long Click Event
        btnLongClick.setOnLongClickListener {
            Toast.makeText(this, "Button Long Pressed!", Toast.LENGTH_LONG).show()
            true // return true to consume the event
        }
    }
}
