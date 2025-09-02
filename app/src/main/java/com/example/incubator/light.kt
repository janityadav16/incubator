package com.example.incubator

import android.os.Bundle
import android.widget.TextView
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class light : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_light)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toggleButton = findViewById<ToggleButton>(R.id.toggleButton)
        val textView = findViewById<TextView>(R.id.toggletextView)
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                textView.text = "Light is ON"
            } else {
                textView.text = "Light is OFF"
            }
        }
    }
}