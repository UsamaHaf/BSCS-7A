package com.techwarsol.bscs_7c

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    lateinit var txtWelcomeText: TextView
    lateinit var etEmailLogin: EditText
    lateinit var etLoginPassword: EditText
    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtWelcomeText = findViewById<TextView>(R.id.txtWelcomeText)
        etEmailLogin = findViewById<EditText>(R.id.etEmailLogin)
        etLoginPassword = findViewById<EditText>(R.id.etLoginPassword)
        btnLogin = findViewById<Button>(R.id.btnLogin)

        val intent = intent.getStringExtra("welcomeText")

        txtWelcomeText.text = intent


        btnLogin.setOnClickListener {

            val getEmail = etEmailLogin.text.toString()
            val getPassword = etLoginPassword.text.toString()


            if(getEmail.isEmpty() || getPassword.isEmpty()){

                Toast.makeText(this@LoginActivity, "Enter All Fields", Toast.LENGTH_SHORT)
                    .show()
            }else{

                Toast.makeText(this, "Success Login", Toast.LENGTH_SHORT)
                    .show()
            }

        }




    }
}