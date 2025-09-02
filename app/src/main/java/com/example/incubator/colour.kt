package com.example.incubator

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class colour : AppCompatActivity() {

    private var currentColor: Int = Color.WHITE
    private var currentAlpha: Int = 255

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_colour)

        // Root layout (background will change here)
        val mainLayout = findViewById<ConstraintLayout>(R.id.main)

        // Buttons
        val btnRed = findViewById<Button>(R.id.buttonRed)
        val btnGreen = findViewById<Button>(R.id.buttonGreen)
        val btnBlue = findViewById<Button>(R.id.buttonBlue)
        val btnYellow = findViewById<Button>(R.id.buttonYellow)
        val btnRandom = findViewById<Button>(R.id.buttonRandomColor)

        // Optional SeekBar for transparency
        val seekBar = findViewById<SeekBar?>(R.id.colorSeekBar)

        // Helper function
        fun applyColor(color: Int) {
            currentColor = color
            val withAlpha = Color.argb(
                currentAlpha,
                Color.red(color),
                Color.green(color),
                Color.blue(color)
            )
            mainLayout.setBackgroundColor(withAlpha)
        }

        // Button actions
        btnRed.setOnClickListener { applyColor(Color.RED) }
        btnGreen.setOnClickListener { applyColor(Color.GREEN) }
        btnBlue.setOnClickListener { applyColor(Color.BLUE) }
        btnYellow.setOnClickListener { applyColor(Color.YELLOW) }
        btnRandom.setOnClickListener {
            val randomColor = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
            applyColor(randomColor)
        }

        // SeekBar to adjust transparency
        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                currentAlpha = progress
                applyColor(currentColor)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Handle system bar padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
