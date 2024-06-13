package financialManagement.personalFinanceManager

import java.util.Scanner

fun main() {
    val financialManager = FinancialManager()
    val scanner = Scanner(System.`in`)

    while (true) {
        println("\nFinancial Manager Menu:")
        println("1. Add Income")
        println("2. Add Expense")
        println("3. Show Balance")
        println("4. Show Transaction History")
        println("5. Exit")
        print("Choose an option: ")

        when (scanner.nextInt()) {
            1 -> {
                print("Enter amount of income: ")
                val amount = scanner.nextDouble()
                financialManager.addIncome(amount)
                println("Income added.")
            }
            2 -> {
                print("Enter amount of expense: ")
                val amount = scanner.nextDouble()
                financialManager.addExpense(amount)
                println("Expense added.")
            }
            3 -> {
                println("Current Balance: ${financialManager.getBalance()}")
            }
            4 -> {
                financialManager.printTransactionHistory()
            }
            5 -> {
                println("Exiting...")
                break
            }
            else -> {
                println("Invalid option. Please try again.")
            }
        }
    }
}
