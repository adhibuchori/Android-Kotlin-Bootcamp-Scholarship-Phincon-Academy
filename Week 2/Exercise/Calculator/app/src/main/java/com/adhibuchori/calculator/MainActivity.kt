package com.adhibuchori.calculator

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.adhibuchori.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var inputValue1: Double? = 0.0
    private var inputValue2: Double? = null
    private var currentOperator: Operator? = null
    private var result: Double? = null
    private val equation: StringBuilder = StringBuilder().append(ZERO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
        setNightModeIndicator()

        adjustStatusBarColor()
    }

    private fun adjustStatusBarColor() {
        val statusBarColor = if (isNightMode()) {
            ContextCompat.getColor(this, R.color.black)
        } else {
            ContextCompat.getColor(this, R.color.display_backgorund)
        }

        setStatusBarColor(statusBarColor)
    }

    private fun setStatusBarColor(color: Int) {
        val window: Window = this.window

        @Suppress("DEPRECATION")
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color

        @Suppress("DEPRECATION")
        if (isNightMode()) {
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        } else {
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    private fun isNightMode(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }

    private fun setListeners() {
        for (button in getNumericButtons()) {
            button.setOnClickListener { onNumberClicked(button.text.toString()) }
        }

        with(binding) {
            btnZero.setOnClickListener { onZeroClicked() }
            btnDoubleZero.setOnClickListener { onDoubleZeroClicked() }
            btnDecimalPoint.setOnClickListener { onDecimalPointClicked() }
            btnAddition.setOnClickListener { onOperatorClicked(Operator.ADDITION) }
            btnSubstraction.setOnClickListener { onOperatorClicked(Operator.SUBSTTRACTION) }
            btnMultiplication.setOnClickListener { onOperatorClicked(Operator.MULTIPLICATION) }
            btnDivision.setOnClickListener { onOperatorClicked(Operator.DIVISION) }
            btnEquals.setOnClickListener { onEqualsClicked() }
            btnAllClear.setOnClickListener { onAllClearClicked() }
            btnPlusMinus.setOnClickListener { onPlusMinusClicked() }
            btnPercentage.setOnClickListener { onPercentageClicked() }
            ivNightMode.setOnClickListener { toggleNightMode() }
        }
    }

    private fun setNightModeIndicator() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            binding.ivNightMode.setImageResource(R.drawable.ic_light)
            binding.cvNightMode.setCardBackgroundColor(ContextCompat.getColor(this, R.color.night_buttons_background))
        } else {
            binding.ivNightMode.setImageResource(R.drawable.ic_night)
        }
    }

    private fun toggleNightMode() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        recreate()
    }

    private fun onPercentageClicked() {
        if (inputValue2 == null) {
            val percentage = getInputValue1() / 100
            inputValue1 = percentage
            equation.clear().append(percentage)
            updateInputOnDisplay()
        } else {
            val percentageOfValue1 = (getInputValue1() * getInputValue2()) / 100
            val percentageOfValue2 = getInputValue2() / 100
            result = when (requireNotNull(currentOperator)) {
                Operator.ADDITION -> getInputValue1() + percentageOfValue1
                Operator.SUBSTTRACTION -> getInputValue1() - percentageOfValue1
                Operator.MULTIPLICATION -> getInputValue1() * percentageOfValue2
                Operator.DIVISION -> getInputValue1() / percentageOfValue2
            }

            equation.clear().append(ZERO)
            updateResultOnDisplay(isPercentage = true)

            inputValue1 = result
            result = null
            inputValue2 = null
            currentOperator = null
        }
    }

    private fun onPlusMinusClicked() {
        if (equation.startsWith(MINUS)) {
            equation.deleteCharAt(0)
        } else {
            equation.insert(0, MINUS)
        }
        setInput()
        updateInputOnDisplay()
    }

    private fun onAllClearClicked() {
        inputValue1 = 0.0
        inputValue2 = null
        currentOperator = null

        result = null
        equation.clear().append(ZERO)
        clearDisplay()
    }

    private fun onOperatorClicked(operator: Operator) {
        onEqualsClicked()
        currentOperator = operator
    }

    private fun onEqualsClicked() {
        if (inputValue2 != null) {
            result = calculate()
            equation.clear().append(ZERO)

            updateResultOnDisplay()

            inputValue1 = result
            result = null
            inputValue2 = null
            currentOperator = null
        } else {
            equation.clear().append(ZERO)
        }
    }

    private fun calculate(): Double {
        return when (requireNotNull(currentOperator)) {
            Operator.ADDITION -> getInputValue1() + getInputValue2()
            Operator.SUBSTTRACTION -> getInputValue1() - getInputValue2()
            Operator.MULTIPLICATION -> getInputValue1() * getInputValue2()
            Operator.DIVISION -> getInputValue1() / getInputValue2()
        }
    }

    private fun onDecimalPointClicked() {
        if (equation.contains(DECIMAL_POINT)) return
        equation.append(DECIMAL_POINT)
        setInput()
        updateInputOnDisplay()
    }

    private fun onZeroClicked() {
        if (equation.startsWith(ZERO)) return
        onNumberClicked(ZERO)
    }

    private fun onDoubleZeroClicked() {
        if (equation.startsWith(ZERO)) return
        onNumberClicked(DOUBLE_ZERO)
    }

    private fun getNumericButtons() = with(binding) {
        arrayOf(
            btnOne,
            btnTwo,
            btnThree,
            btnFour,
            btnFive,
            btnSix,
            btnSeven,
            btnEight,
            btnNine
        )
    }

    private fun onNumberClicked(numberText: String) {
        if (equation.startsWith(ZERO)) {
            equation.deleteCharAt(0)
        } else if (equation.startsWith("$MINUS$ZERO")) {
            equation.deleteCharAt(1)
        }
        equation.append(numberText)
        setInput()
        updateInputOnDisplay()
    }

    private fun setInput() {
        if (currentOperator == null) {
            inputValue1 = equation.toString().toDouble()
        } else {
            inputValue2 = equation.toString().toDouble()
        }
    }

    private fun clearDisplay() {
        with(binding) {
            tvTextInput.text = getFormattedDisplayValue(value = getInputValue1())
            tvTextEquation.text = null
        }
    }

    private fun updateResultOnDisplay(isPercentage: Boolean = false) {

        binding.tvTextInput.text = getFormattedDisplayValue(value = result)

        var input2Text = getFormattedDisplayValue(value = getInputValue2())

        if (isPercentage) input2Text = "$input2Text${getString(R.string.percentage)}"
        binding.tvTextEquation.text = String.format(
            "%s %s %s",
            getFormattedDisplayValue(value = getInputValue1()),
            getOperatorSymbol(),
            input2Text
        )
    }

    private fun updateInputOnDisplay() {
        if (result == null) {
            binding.tvTextEquation.text = null
        }
        binding.tvTextInput.text = equation
    }

    private fun getInputValue1() = inputValue1 ?: 0.0
    private fun getInputValue2() = inputValue2 ?: 0.0

    private fun getOperatorSymbol(): String {
        return when (requireNotNull(currentOperator)) {
            Operator.ADDITION -> getString(R.string.addition)
            Operator.SUBSTTRACTION -> getString(R.string.substraction)
            Operator.MULTIPLICATION -> getString(R.string.multiplication)
            Operator.DIVISION -> getString(R.string.division)
        }
    }

    private fun getFormattedDisplayValue(value: Double?): String? {

        val originalValue = value ?: return null

        return if (originalValue % 1 == 0.0) {
            originalValue.toInt().toString()
        } else {
            originalValue.toString()
        }
    }

    enum class Operator {
        ADDITION, SUBSTTRACTION, MULTIPLICATION, DIVISION
    }

    private companion object {
        const val DECIMAL_POINT = "."
        const val ZERO = "0"
        const val DOUBLE_ZERO = "00"
        const val MINUS = "-"
    }
}