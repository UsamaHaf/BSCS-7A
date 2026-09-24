package com.techwarsol.bscs_7c

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.techwarsol.bscs_7c.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDOBPicker()

        binding.btnSignUP.setOnClickListener {

            val userName = binding.etUserName.text.toString()
            val phone = binding.etPhone.text.toString()
            val email = binding.etEmail.text.toString()
            val dob = binding.etDOB.text.toString()
            val password = binding.etPassword.toString()

            if (userName.isEmpty()) {
                Toast.makeText(this@RegistrationActivity, "Enter You UserName", Toast.LENGTH_SHORT)
                    .show()

            } else if (phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this@RegistrationActivity,
                    "Enter All Fileds Data",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val intent = Intent(this@RegistrationActivity , LoginActivity::class.java)

                intent.putExtra("welcomeText", "Welcome Mr. ${userName}")

                startActivity(intent)

                Toast.makeText(this@RegistrationActivity, "SignUp Success", Toast.LENGTH_SHORT)
                    .show()


            }


        }


    }

    private fun setDOBPicker() {
        binding.etDOB.setOnClickListener {

            val calendar = Calendar.getInstance()

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val date = calendar.get(Calendar.DATE)

            val datePicker = DatePickerDialog(
                this, { _, selectedYear, selectedMonth, selectedDay ->
                    val date = "$selectedDay, $selectedMonth, $selectedYear"
                    binding.etDOB.setText(date)
                }, year, month, date
            )

            datePicker.datePicker.maxDate = System.currentTimeMillis()
            datePicker.show()
        }
    }
}
