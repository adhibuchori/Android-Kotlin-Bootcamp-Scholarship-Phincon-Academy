package financialManagement.personalFinanceManager

class FinancialManager {
    private val transactions = mutableListOf<Transaction>()
    private var balance = 0.0

    fun addIncome(amount: Double) {
        transactions.add(Transaction("Income", amount))
        balance += amount
    }

    fun addExpense(amount: Double) {
        transactions.add(Transaction("Expense", amount))
        balance -= amount
    }

    fun getBalance(): Double {
        return balance
    }

    fun printTransactionHistory() {
        if (transactions.isEmpty()) {
            println("No transaction history available.")
        } else {
            println("Transaction History:")
            transactions.forEachIndexed { index, transaction ->
                println("${index + 1}. ${transaction.type}: ${transaction.amount}")
            }
        }
    }
}