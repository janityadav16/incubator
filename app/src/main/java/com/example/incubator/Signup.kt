package com.example.incubator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editNewUsername = findViewById<EditText>(R.id.editNewUsername)
        val editNewPassword = findViewById<EditText>(R.id.editNewPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val loginText = findViewById<TextView>(R.id.loginText)

        btnRegister.setOnClickListener {
            val newUser = editNewUsername.text.toString()
            val newPass = editNewPassword.text.toString()
            if (newUser.isEmpty() || newPass.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show()
                // finish() // go back to LoginActivity
            }
        }
        loginText.setOnClickListener {
            val intent = Intent(this, loginText::class.java)
            startActivity(intent)
        }
    }
}