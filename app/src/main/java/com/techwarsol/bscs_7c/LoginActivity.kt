package com.techwarsol.bscs_7c

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var txtWelcomeText: TextView
    lateinit var etEmailLogin: EditText
    lateinit var etLoginPassword: EditText
    lateinit var btnLogin: Button

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Firebase Authentication
        auth = FirebaseAuth.getInstance()

        txtWelcomeText = findViewById(R.id.txtWelcomeText)
        etEmailLogin = findViewById(R.id.etEmailLogin)
        etLoginPassword = findViewById(R.id.etLoginPassword)
        btnLogin = findViewById(R.id.btnLogin)

        val welcomeText = intent.getStringExtra("welcomeText")
        txtWelcomeText.text = welcomeText

        btnLogin.setOnClickListener {

            // Get values from EditTexts
            val email = etEmailLogin.text.toString().trim()
            val password = etLoginPassword.text.toString()

            // Check empty fields
            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                    this,
                    "Enter All Fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Firebase Login
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        Toast.makeText(
                            this,
                            "Login Successful",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Go to Home Page
                        val intent = Intent(
                            this,
                            HomePageActivity::class.java
                        )

                        startActivity(intent)
                        finish()

                    } else {

                        // Show actual Firebase error
                        val errorMessage =
                            task.exception?.message ?: "Unknown error"

                        Toast.makeText(
                            this,
                            "Login Failed: $errorMessage",
                            Toast.LENGTH_LONG
                        ).show()

                        Log.e(
                            "LOGIN_ERROR",
                            "Firebase Login Failed",
                            task.exception
                        )
                    }
                }
        }
    }
}