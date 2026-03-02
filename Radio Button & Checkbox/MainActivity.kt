package com.example.registrationform

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupGender)
        val rbMale = findViewById<RadioButton>(R.id.rbMale)
        val rbFemale = findViewById<RadioButton>(R.id.rbFemale)
        val rbOther = findViewById<RadioButton>(R.id.rbOther)
        val cbTerms = findViewById<CheckBox>(R.id.cbTerms)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {

            // Get selected gender
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(this, "Please select a gender!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val gender = when (selectedId) {
                R.id.rbMale -> "Male"
                R.id.rbFemale -> "Female"
                R.id.rbOther -> "Other"
                else -> "Not Selected"
            }

            // Check if terms accepted
            if (!cbTerms.isChecked) {
                Toast.makeText(this, "Please accept the Terms & Conditions!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Show result
            val message = "Gender: $gender\nTerms Accepted: Yes"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}
