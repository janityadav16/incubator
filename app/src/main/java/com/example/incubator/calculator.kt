package com.example.incubator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class calculator : AppCompatActivity() {

    private lateinit var resultText: TextView
    private var input: String = ""   // stores expression
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculator)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resultText = findViewById(R.id.resultText)

        // Digits
        val digits = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )
        for (id in digits) {
            findViewById<Button>(id).setOnClickListener { onDigit((it as Button).text.toString()) }
        }

        // Operators
        findViewById<Button>(R.id.btnPlus).setOnClickListener { onOperator("+") }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { onOperator("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { onOperator("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { onOperator("/") }

        // Dot
        findViewById<Button>(R.id.btnDot).setOnClickListener { onDot() }

        // Clear
        findViewById<Button>(R.id.btnClear).setOnClickListener { onClear() }

        // Backspace
        findViewById<Button>(R.id.btnBack).setOnClickListener { onBack() }

        // Equals
        findViewById<Button>(R.id.btnEquals).setOnClickListener { onEqual() }
    }

    private fun onDigit(digit: String) {
        input += digit
        resultText.text = input
        lastNumeric = true
    }

    private fun onOperator(op: String) {
        if (lastNumeric) {
            input += op
            resultText.text = input
            lastNumeric = false
            lastDot = false
        }
    }

    private fun onDot() {
        if (lastNumeric && !lastDot) {
            input += "."
            resultText.text = input
            lastNumeric = false
            lastDot = true
        }
    }

    private fun onClear() {
        input = ""
        resultText.text = "0"
        lastNumeric = false
        lastDot = false
    }

    private fun onBack() {
        if (input.isNotEmpty()) {
            input = input.dropLast(1)
            resultText.text = if (input.isEmpty()) "0" else input
        }
    }

    private fun onEqual() {
        try {
            val expression = input.replace("÷", "/").replace("×", "*")
            val result = eval(expression)  // simple evaluator
            resultText.text = result.toString()
            input = result.toString()
        } catch (e: Exception) {
            resultText.text = "Error"
        }
    }

    // Basic expression evaluator
    private fun eval(expr: String): Double {
        return object : Any() {
            var pos = -1
            var ch: Int = 0

            fun nextChar() { ch = if (++pos < expr.length) expr[pos].code else -1 }
            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < expr.length) throw RuntimeException("Unexpected: " + expr[pos])
                return x
            }

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    when {
                        eat('+'.code) -> x += parseTerm()
                        eat('-'.code) -> x -= parseTerm()
                        else -> return x
                    }
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    when {
                        eat('*'.code) -> x *= parseFactor()
                        eat('/'.code) -> x /= parseFactor()
                        else -> return x
                    }
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.code)) return parseFactor() // unary plus
                if (eat('-'.code)) return -parseFactor() // unary minus

                var x: Double
                val startPos = pos
                if (eat('('.code)) { // parentheses
                    x = parseExpression()
                    eat(')'.code)
                } else if (ch in '0'.code..'9'.code || ch == '.'.code) {
                    while (ch in '0'.code..'9'.code || ch == '.'.code) nextChar()
                    x = expr.substring(startPos, pos).toDouble()
                } else {
                    throw RuntimeException("Unexpected: " + ch.toChar())
                }
                return x
            }
        }.parse()
    }
}
