
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Puts Menu Item into the ⋮ right corner overflow menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // Toast on sub item tap
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sub_item1 -> Toast.makeText(this, "sub item1", Toast.LENGTH_SHORT).show()
            R.id.sub_item2 -> Toast.makeText(this, "sub item2", Toast.LENGTH_SHORT).show()
            R.id.sub_item3 -> Toast.makeText(this, "sub item3", Toast.LENGTH_SHORT).show()
            R.id.sub_item4 -> Toast.makeText(this, "sub item4", Toast.LENGTH_SHORT).show()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}
