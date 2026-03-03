import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var lvAnimals: ListView

    private val animals = arrayOf(
        "Lion",
        "Tiger",
        "Elephant",
        "Dog",
        "Cat",
        "Horse",
        "Monkey",
        "Bear",
        "Deer",
        "Rabbit"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvAnimals = findViewById(R.id.lvAnimals)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            animals
        )

        lvAnimals.adapter = adapter

        lvAnimals.setOnItemClickListener { _, _, position, _ ->
            val selectedAnimal = animals[position]
            Toast.makeText(
                this,
                "You selected $selectedAnimal",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
