package com.example.calculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView

    private val buttons = arrayOf(
        "DEL","C","%","+",
        "1","2","3","/",
        "4","5","6","-",
        "7","8","9","*",
        ".","0","="
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.show)
        val gridView: GridView = findViewById(R.id.gridView)

        val adapter = ArrayAdapter(this, R.layout.grid_item, R.id.btnText, buttons)
        gridView.adapter = adapter

        gridView.setOnItemClickListener { _, _, position, _ ->
            val value = buttons[position]

            when (value) {
                "C" -> display.text = "0"
                "DEL" -> {
                    if (display.text.length > 1)
                        display.text = display.text.dropLast(1)
                    else
                        display.text = "0"
                }
                "%" -> {
                    try {
                        val num = display.text.toString().toDouble()
                        display.text = (num / 100).toString()
                    } catch (e: Exception) {
                        display.text = "Error"
                    }
                }
                "=" -> calculate()
                else -> {
                    if (display.text == "0") display.text = value
                    else display.append(value)
                }
            }
        }
    }

    private fun calculate() {
        try {
            val result = eval(display.text.toString())
            display.text = result.toString()
        } catch (e: Exception) {
            display.text = "Error"
        }
    }

    // Simple expression evaluator
    private fun eval(expr: String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0

            fun nextChar() {
                ch = if (++pos < expr.length) expr[pos].code else -1
            }

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
                val startPos = pos
                if (eat('+'.code)) return parseFactor()
                if (eat('-'.code)) return -parseFactor()

                while (ch in '0'.code..'9'.code || ch == '.'.code) nextChar()
                return expr.substring(startPos, pos).toDouble()
            }
        }.parse()
    }
}
