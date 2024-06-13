package calculatorHistory

import java.util.Scanner

data class Calculation(val operation: String, val result: Double)

class Calculator {

    private val history = mutableListOf<Calculation>()

    fun add(a: Double, b: Double): Double {
        val result = a + b
        saveToHistory("$a + $b", result)
        return result
    }

    fun subtract(a: Double, b: Double): Double {
        val result = a - b
        saveToHistory("$a - $b", result)
        return result
    }

    fun multiply(a: Double, b: Double): Double {
        val result = a * b
        saveToHistory("$a * $b", result)
        return result
    }

    fun divide(a: Double, b: Double): Double {
        if (b == 0.0) {
            println("Error: Division by zero")
            return Double.NaN
        }
        val result = a / b
        saveToHistory("$a / $b", result)
        return result
    }

    private fun saveToHistory(operation: String, result: Double) {
        history.add(Calculation(operation, result))
    }

    fun printHistory() {
        if (history.isEmpty()) {
            println("No history available.")
        } else {
            println("History:")
            history.forEachIndexed { index, calculation ->
                println("${index + 1}. ${calculation.operation} = ${calculation.result}")
            }
        }
    }
}

fun main() {
    val calculator = Calculator()
    val scanner = Scanner(System.`in`)

    while (true) {
        println("\nCalculator Menu:")
        println("1. Add")
        println("2. Subtract")
        println("3. Multiply")
        println("4. Divide")
        println("5. Show History")
        println("6. Exit")
        print("Choose an option: ")

        when (scanner.nextInt()) {
            1 -> {
                print("Enter first number: ")
                val num1 = scanner.nextDouble()
                print("Enter second number: ")
                val num2 = scanner.nextDouble()
                println("Result: ${calculator.add(num1, num2)}")
            }
            2 -> {
                print("Enter first number: ")
                val num1 = scanner.nextDouble()
                print("Enter second number: ")
                val num2 = scanner.nextDouble()
                println("Result: ${calculator.subtract(num1, num2)}")
            }
            3 -> {
                print("Enter first number: ")
                val num1 = scanner.nextDouble()
                print("Enter second number: ")
                val num2 = scanner.nextDouble()
                println("Result: ${calculator.multiply(num1, num2)}")
            }
            4 -> {
                print("Enter first number: ")
                val num1 = scanner.nextDouble()
                print("Enter second number: ")
                val num2 = scanner.nextDouble()
                println("Result: ${calculator.divide(num1, num2)}")
            }
            5 -> {
                calculator.printHistory()
            }
            6 -> {
                println("Exiting...")
                break
            }
            else -> {
                println("Invalid option. Please try again.")
            }
        }
    }
}