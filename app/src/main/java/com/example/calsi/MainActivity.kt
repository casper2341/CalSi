package com.example.calsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvinput: TextView? = null
    var lastNumeric: Boolean = false
    var lastdot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvinput = findViewById(R.id.tvinput)
    }

    fun onDigit(view: View) {
        tvinput?.append((view as Button).text)
        lastNumeric = true
        lastdot = false
    }

    fun clr(view: View) {
        tvinput?.text = ""
    }

    fun onDecimal(view: View) {
        if (lastNumeric && !lastdot) {
            tvinput?.append(".")
            lastNumeric = false
            lastdot = true
        }
    }


    fun onOperator(view: View) {
        tvinput?.text?.let {

            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvinput?.append((view as Button).text)
                lastNumeric = false
                lastdot = false
            }
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = tvinput?.text.toString()

            var prefix = ""

            try {

                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")) {
                    val splitVaalue = tvValue.split("-")
                    var one = splitVaalue[0]
                    var two = splitVaalue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvinput?.text = removezeero((one.toDouble() - two.toDouble()).toString())

                } else if (tvValue.contains("+")) {
                    val splitVaalue = tvValue.split("+")
                    var one = splitVaalue[0]
                    var two = splitVaalue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvinput?.text = removezeero((one.toDouble() + two.toDouble()).toString())

                } else if (tvValue.contains("*")) {
                    val splitVaalue = tvValue.split("*")
                    var one = splitVaalue[0]
                    var two = splitVaalue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvinput?.text = removezeero((one.toDouble() * two.toDouble()).toString())

                } else if (tvValue.contains("/")) {
                    val splitVaalue = tvValue.split("/")
                    var one = splitVaalue[0]
                    var two = splitVaalue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvinput?.text = removezeero((one.toDouble() / two.toDouble()).toString())

                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removezeero(result: String): String {
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length - 2)

        return value
    }

    private fun isOperatorAdded(value: String): Boolean {

        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("+")
                    || value.contains("*")
                    || value.contains("/")
                    || value.contains("-")
        }
    }


}