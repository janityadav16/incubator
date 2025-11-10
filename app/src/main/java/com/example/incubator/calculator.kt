package com.example.incubator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
class calculator : AppCompatActivity() {

    private lateinit var textViewInput: TextView
    private var input = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculator)

        textViewInput = findViewById(R.id.textViewInput)

        // Number buttons
        val numbers = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot
        )

        for (id in numbers) {
            findViewById<Button>(id).setOnClickListener {
                val value = (it as Button).text.toString()
                input += value
                textViewInput.text = input
            }
        }

        // Operator buttons
        val operators = listOf(
            R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv, R.id.btnMod
        )

        for (id in operators) {
            findViewById<Button>(id).setOnClickListener {
                val value = (it as Button).text.toString()
                input += " $value "
                textViewInput.text = input
            }
        }

        // Clear button
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            input = ""
            textViewInput.text = "0"
        }

        // Equal button
        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            try {
                val result = evaluateExpression(input)
                textViewInput.text = result.toString()
                input = result.toString()
            } catch (e: Exception) {
                textViewInput.text = "Error"
                input = ""
            }
        }
    }

    // Custom evaluation function
    private fun evaluateExpression(expression: String): Double {
        val tokens = expression.trim().split(" ").toMutableList()
        if (tokens.isEmpty()) return 0.0

        // Pass 1: handle × ÷ %
        var i = 0
        while (i < tokens.size) {
            when (tokens[i]) {
                "×", "÷", "%" -> {
                    val left = tokens[i - 1].toDouble()
                    val right = tokens[i + 1].toDouble()
                    val result = when (tokens[i]) {
                        "×", "*" -> left * right
                        "÷", "/" -> left / right
                        "%" -> left % right
                        else -> 0.0
                    }
                    // Replace [left, operator, right] with result
                    tokens[i - 1] = result.toString()
                    tokens.removeAt(i) // remove operator
                    tokens.removeAt(i) // remove right number
                    i -= 1
                }
            }
            i++
        }

        // Pass 2: handle + -
        var result = tokens[0].toDouble()
        i = 1
        while (i < tokens.size) {
            val operator = tokens[i]
            val nextNumber = tokens[i + 1].toDouble()
            when (operator) {
                "+" -> result += nextNumber
                "-" -> result -= nextNumber
            }
            i += 2
        }

        return result
    }
}
