import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Track which image is currently showing
    // true  = showing image 1
    // false = showing image 2
    private var isImage1 = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvStatus  = findViewById<TextView>(R.id.tvStatus)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val btnChange = findViewById<Button>(R.id.btnChange)

        // Set first image on launch
        imageView.setImageResource(R.drawable.ic_launcher_foreground)

        btnChange.setOnClickListener {

            // Toggle between image 1 and image 2 on every click
            if (isImage1) {
                // Switch to image 2
                imageView.setImageResource(R.drawable.ic_launcher_background)
                tvStatus.text = "Image 2"
            } else {
                // Switch back to image 1
                imageView.setImageResource(R.drawable.ic_launcher_foreground)
                tvStatus.text = "Image 1"
            }

            // Flip the flag for next click
            isImage1 = !isImage1
        }
    }
}
