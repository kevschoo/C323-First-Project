package com.example.c323firstproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log

class MainActivity : AppCompatActivity() {

    // All variables for the math and string logic for the calculator
    lateinit var textView: TextView
    var numberString: String = "0"
    var firstNumber: Double = 0.0
    var secondNumber: Double = 0.0
    var result: Double = 0.0
    var currentOperator: String = ""


    // This function is called on start of the app, it will locate all the buttons from the IDs
    // Then it will assign each function to the correct button
    // It also adds the log to each button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.txt_answer)

        val buttonZero: Button = findViewById(R.id.btn_zero)
        val buttonOne: Button = findViewById(R.id.btn_one)
        val buttonTwo: Button = findViewById(R.id.btn_two)
        val buttonThree: Button = findViewById(R.id.btn_three)
        val buttonFour: Button = findViewById(R.id.btn_four)
        val buttonFive: Button = findViewById(R.id.btn_five)
        val buttonSix: Button = findViewById(R.id.btn_six)
        val buttonSeven: Button = findViewById(R.id.btn_seven)
        val buttonEight: Button = findViewById(R.id.btn_eight)
        val buttonNine: Button = findViewById(R.id.btn_nine)

        val buttonAdd: Button = findViewById(R.id.btn_add)
        val buttonSubtract: Button = findViewById(R.id.btn_subtract)
        val buttonMultiply: Button = findViewById(R.id.btn_multiply)
        val buttonDivide: Button = findViewById(R.id.btn_divide)
        val buttonEqual: Button = findViewById(R.id.btn_equal)
        val buttonClear: Button = findViewById(R.id.btn_clear)
        val buttonPercent: Button = findViewById(R.id.btn_percent)
        val buttonSwitchSigns: Button = findViewById(R.id.btn_switch_signs)
        val buttonPeriod: Button = findViewById(R.id.btn_period)

        val buttonSin: Button? = findViewById(R.id.btn_sin)
        val buttonCos: Button? = findViewById(R.id.btn_cos)
        val buttonTan: Button? = findViewById(R.id.btn_tan)
        val buttonLog: Button? = findViewById(R.id.btn_log10)
        val buttonLogNatural: Button? = findViewById(R.id.btn_logNatural)

        buttonZero.setOnClickListener {
            appendOnClick("0")
            Log.d(TAG, "Button 0 clicked")
        }
        buttonOne.setOnClickListener {
            appendOnClick("1")
            Log.d(TAG, "Button 1 clicked")
        }
        buttonTwo.setOnClickListener {
            appendOnClick("2")
            Log.d(TAG, "Button 2 clicked")
        }
        buttonThree.setOnClickListener {
            appendOnClick("3")
            Log.d(TAG, "Button 3 clicked")
        }
        buttonFour.setOnClickListener {
            appendOnClick("4")
            Log.d(TAG, "Button 4 clicked")
        }
        buttonFive.setOnClickListener {
            appendOnClick("5")
            Log.d(TAG, "Button 5 clicked")
        }
        buttonSix.setOnClickListener {
            appendOnClick("6")
            Log.d(TAG, "Button 6 clicked")
        }
        buttonSeven.setOnClickListener {
            appendOnClick("7")
            Log.d(TAG, "Button 7 clicked")
        }
        buttonEight.setOnClickListener {
            appendOnClick("8")
            Log.d(TAG, "Button 8 clicked")
        }
        buttonNine.setOnClickListener {
            appendOnClick("9")
            Log.d(TAG, "Button 9 clicked")
        }
        buttonPeriod.setOnClickListener {
            appendOnClick(".")
            Log.d(TAG, "Button Period clicked")
        }

        buttonAdd.setOnClickListener {
            add()
            Log.d(TAG, "Button Add clicked")
        }
        buttonSubtract.setOnClickListener {
            subtract()
            Log.d(TAG, "Button Subtract clicked")
        }
        buttonMultiply.setOnClickListener {
            multiply()
            Log.d(TAG, "Button Multiply clicked")
        }
        buttonDivide.setOnClickListener {
            divide()
            Log.d(TAG, "Button Divide clicked")
        }

        buttonSin?.setOnClickListener {
            sin()
            Log.d(TAG, "Button Sin clicked")
        }
        buttonCos?.setOnClickListener {
            cos()
            Log.d(TAG, "Button Cos clicked")
        }
        buttonTan?.setOnClickListener {
            tan()
            Log.d(TAG, "Button Tan clicked")
        }
        buttonLog?.setOnClickListener {
            log()
            Log.d(TAG, "Button Log 10 clicked")
        }
        buttonLogNatural?.setOnClickListener {
            naturalLog()
            Log.d(TAG, "Button Ln clicked")
        }

        buttonClear.setOnClickListener {
            clear()
            Log.d(TAG, "Button Clear clicked")
        }
        buttonEqual.setOnClickListener {
            calculate()
            Log.d(TAG, "Button Equal clicked")
        }
        buttonPercent.setOnClickListener {
            percent()
            Log.d(TAG, "Button Percent clicked")
        }
        buttonSwitchSigns.setOnClickListener {
            switchSigns()
            Log.d(TAG, "Button Switch Signs clicked")
        }

        if (savedInstanceState != null) {
            numberString = savedInstanceState.getString("numberString") ?: "0"
            firstNumber = savedInstanceState.getDouble("firstNumber")
            secondNumber = savedInstanceState.getDouble("secondNumber")
            result = savedInstanceState.getDouble("result")
            currentOperator = savedInstanceState.getString("currentOperator") ?: ""
            textView.text = numberString
        }
    }

    //Helps filter the logs of the app
    companion object {
        private const val TAG = "CalculatorApp"
    }

    //Saves the needed variables for math calculations and the text
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("numberString", numberString)
        outState.putDouble("firstNumber", firstNumber)
        outState.putDouble("secondNumber", secondNumber)
        outState.putDouble("result", result)
        outState.putString("currentOperator", currentOperator)
    }

    //When the activity is restored, it will load the saved data for math calculations
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        numberString = savedInstanceState.getString("numberString") ?: "0"
        firstNumber = savedInstanceState.getDouble("firstNumber")
        secondNumber = savedInstanceState.getDouble("secondNumber")
        result = savedInstanceState.getDouble("result")
        currentOperator = savedInstanceState.getString("currentOperator") ?: ""
        textView.text = numberString
    }

    // Function that checks if there is a value to add to the current number
    // If there is a value we append it to make the new number
    // Takes a String input, adds that to the numberString variable
    private fun appendOnClick(string: String) {
        if (numberString == "0") {
            numberString = string
        } else if (string == "." && numberString.contains(".")) {
            // Do nothing since we can't have double decimals
        } else {
            numberString += string
        }
        textView.text = numberString
    }

    // Function that adds the current numberString being made to the first number.
    private fun add() {
        if (numberString.isNotEmpty()) {
            firstNumber = numberString.toDouble()
            currentOperator = "+"
            numberString = "0"
        }
    }

    // Function that subtracts the current numberString from the firstNumber
    private fun subtract() {
        if (numberString.isNotEmpty()) {
            firstNumber = numberString.toDouble()
            currentOperator = "-"
            numberString = "0"
        }
    }

    // Function that multiplies the current numberString to the first number
    private fun multiply() {
        if (numberString.isNotEmpty()) {
            firstNumber = numberString.toDouble()
            currentOperator = "*"
            numberString = "0"
        }
    }

    // Function that divides the first number by the numberString
    private fun divide() {
        if (numberString.isNotEmpty()) {
            firstNumber = numberString.toDouble()
            currentOperator = "/"
            numberString = "0"
        }
    }

    // Function that clears the variables of the calculator
    private fun clear() {
        numberString = "0"
        firstNumber = 0.0
        secondNumber = 0.0
        result = 0.0
        currentOperator = ""
        textView.text = numberString
    }

    // Function that calculates the interactions between the first number which was the old result
    // and the new number which is the current numberString.
    // based on the operation it will do the math then display it on the text
    private fun calculate() {
        if (numberString.isNotEmpty() && firstNumber != 0.0) {
            secondNumber = numberString.toDouble()
            result = when (currentOperator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "/" -> if (secondNumber != 0.0) firstNumber / secondNumber else 0.0
                else -> 0.0
            }
            textView.text = result.toString()
            numberString = result.toString()
            firstNumber = 0.0
            secondNumber = 0.0
            currentOperator = ""
        }
    }

    // Function that divides the current numberString by 100 to make a percent
    private fun percent() {
        if (numberString.isNotEmpty()) {
            val num = numberString.toDouble()
            result = num / 100
            textView.text = result.toString()
            numberString = result.toString()
        }
    }

    // Function that swaps the signs of the number from + to - or - to +
    private fun switchSigns() {
        if (numberString.isNotEmpty()) {
            val num = numberString.toDouble()
            result = num * -1
            textView.text = result.toString()
            numberString = result.toString()
        }
    }

    // Function that takes the sin of the current number
    private fun sin() {
        if (numberString.isNotEmpty()) {
            val num = numberString.toDouble()
            result = kotlin.math.sin(Math.toRadians(num))
            textView.text = result.toString()
            numberString = result.toString()
        }
    }

    // Function that takes the cos of the current number
    private fun cos() {
        if (numberString.isNotEmpty()) {
            val num = numberString.toDouble()
            result = kotlin.math.cos(Math.toRadians(num))
            textView.text = result.toString()
            numberString = result.toString()
        }
    }

    // Function that takes the tan of the current number
    private fun tan() {
        if (numberString.isNotEmpty()) {
            val num = numberString.toDouble()
            result = kotlin.math.tan(Math.toRadians(num))
            textView.text = result.toString()
            numberString = result.toString()
        }
    }

    // Function that takes the log of the current number only if it is positive
    private fun log() {
        if (numberString.isNotEmpty()) {
            val num = numberString.toDouble()
            if (num > 0) {
                result = kotlin.math.log10(num)
                textView.text = result.toString()
                numberString = result.toString()
            }
        }
    }

    // Function that takes the natural log of the current number only if it is positive
    private fun naturalLog() {
        if (numberString.isNotEmpty()) {
            val num = numberString.toDouble()
            if (num > 0) {
                result = kotlin.math.ln(num)
                textView.text = result.toString()
                numberString = result.toString()
            }
        }
    }

}