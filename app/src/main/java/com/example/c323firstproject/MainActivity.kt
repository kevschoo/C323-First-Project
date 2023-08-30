package com.example.c323firstproject

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        buttonZero.setOnClickListener { appendOnClick("0") }
        buttonOne.setOnClickListener { appendOnClick("1") }
        buttonTwo.setOnClickListener { appendOnClick("2") }
        buttonThree.setOnClickListener { appendOnClick("3") }
        buttonFour.setOnClickListener { appendOnClick("4") }
        buttonFive.setOnClickListener { appendOnClick("5") }
        buttonSix.setOnClickListener { appendOnClick("6") }
        buttonSeven.setOnClickListener { appendOnClick("7") }
        buttonEight.setOnClickListener { appendOnClick("8") }
        buttonNine.setOnClickListener { appendOnClick("9") }
        buttonPeriod.setOnClickListener { appendOnClick(".") }

        buttonAdd.setOnClickListener { add() }
        buttonSubtract.setOnClickListener { subtract() }
        buttonMultiply.setOnClickListener { multiply() }
        buttonDivide.setOnClickListener { divide() }

        buttonClear.setOnClickListener { clear() }
        buttonEqual.setOnClickListener { calculate() }
        buttonPercent.setOnClickListener { percent() }
        buttonSwitchSigns.setOnClickListener { switchSigns() }
    }

    // Function that checks if there is a value to add to the current number
    // If there is a value we append it to make the new number
    private fun appendOnClick(string: String) {
        if (numberString == "0") {
            numberString = string
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

}