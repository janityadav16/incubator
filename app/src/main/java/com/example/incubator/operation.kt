package com.example.incubator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class operation : AppCompatActivity() {
    private var count = 0
    private var toggle = true
    private var number = 1 // Initial value for multiplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_operation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView = findViewById<TextView>(R.id.textView)
        val btnMessage = findViewById<Button>(R.id.btnMessage)
        val btnCounter = findViewById<Button>(R.id.btnCounter)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val btnChangeText = findViewById<Button>(R.id.btnChangeText)
        val btnTime = findViewById<Button>(R.id.btnTime)
        val btnRandom = findViewById<Button>(R.id.btnRandom)
        val btnToggle = findViewById<Button>(R.id.btnToggle)
        val btnGreeting = findViewById<Button>(R.id.btnGreeting)
        val btnLength = findViewById<Button>(R.id.btnLength)
        val btnMultiply = findViewById<Button>(R.id.btnMultiply)

        // 1. Show message
        btnMessage.setOnClickListener {
            textView.text = "Button Clicked!"
        }

        // 2. Counter++
        btnCounter.setOnClickListener {
            count++
            textView.text = "Count: $count"
        }

        // 3. Reset counter
        btnReset.setOnClickListener {
            count = 0
            textView.text = "Count reset: $count"
        }

        // 4. Change text
        btnChangeText.setOnClickListener {
            textView.text = "Text Changed!"
        }

        // 5. Show time
        btnTime.setOnClickListener {
            val time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
            textView.text = "Time: $time"
        }

        // 6. Random number
        btnRandom.setOnClickListener {
            val randomNumber = Random.nextInt(1, 100)
            textView.text = "Random: $randomNumber"
        }

        // 7. Toggle text
        btnToggle.setOnClickListener {
            if (toggle) {
                textView.text = "Hello"
            } else {
                textView.text = "Welcome"
            }
            toggle = !toggle
        }

        // 8. Greeting
        btnGreeting.setOnClickListener {
            textView.text = "Good Morning!"
        }

        // 9. Word length
        btnLength.setOnClickListener {
            val word = "Android"
            textView.text = "Length of '$word': ${word.length}"
        }

        // 10. Multiply by 2
        btnMultiply.setOnClickListener {
            number *= 2
            textView.text = "Value: $number"
        }
    }
}