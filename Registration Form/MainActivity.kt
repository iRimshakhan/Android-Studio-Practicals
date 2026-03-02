package com.example.registrationform

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var genderGroup: RadioGroup
    private lateinit var dob: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var terms: CheckBox
    private lateinit var profileImage: ImageView

    private val PICK_IMAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        genderGroup = findViewById(R.id.genderGroup)
        dob = findViewById(R.id.dob)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirmPassword)
        email = findViewById(R.id.email)
        phone = findViewById(R.id.phone)
        terms = findViewById(R.id.terms)
        profileImage = findViewById(R.id.profileImage)

        val spinner: Spinner = findViewById(R.id.countrySpinner)
        val submitBtn: com.google.android.material.floatingactionbutton.FloatingActionButton =
            findViewById(R.id.submitBtn)

        // Spinner Data
        val countries = arrayOf("India", "USA", "UK", "Canada")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, countries)

        // Date Picker
        dob.setOnClickListener {
            val cal = Calendar.getInstance()
            val dp = DatePickerDialog(this,
                { _, year, month, day ->
                    dob.setText("$day/${month + 1}/$year")
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )
            dp.show()
        }

        // Select Profile Image
        profileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE)
        }

        // Submit Button
        submitBtn.setOnClickListener {

            if (genderGroup.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (dob.text.toString().isEmpty()) {
                Toast.makeText(this, "Select Date of Birth", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

             if (phone.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.text.toString() != confirmPassword.text.toString()) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!terms.isChecked) {
                Toast.makeText(this, "Accept Terms & Conditions", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            val imageUri: Uri? = data?.data
            profileImage.setImageURI(imageUri)
        }
    }
}
