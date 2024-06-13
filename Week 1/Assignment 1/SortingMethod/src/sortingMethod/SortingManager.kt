package sortingMethod

class SortingManager {

    private val history = mutableListOf<Sorting>()

    fun sortingAscendingBuiltIn(numbers: List<Double>): List<Double> {
        val sortedNumbers = numbers.sorted()
        saveSortingHistory("Built-in Ascending", sortedNumbers)
        return sortedNumbers
    }

    fun sortingDescendingBuiltIn(numbers: List<Double>): List<Double> {
        val sortedNumbers = numbers.sortedDescending()
        saveSortingHistory("Built-in Descending", sortedNumbers)
        return sortedNumbers
    }

    fun sortingAscendingManual(numbers: List<Double>): List<Double> {
        val sortedNumbers = bubbleSort(numbers, ascending = true)
        saveSortingHistory("Bubble Sort Ascending", sortedNumbers)
        return sortedNumbers
    }

    fun sortingDescendingManual(numbers: List<Double>): List<Double> {
        val sortedNumbers = bubbleSort(numbers, ascending = false)
        saveSortingHistory("Bubble Sort Descending", sortedNumbers)
        return sortedNumbers
    }

    private fun bubbleSort(numbers: List<Double>, ascending: Boolean): List<Double> {
        val sortedNumbers = numbers.toMutableList()
        val n = sortedNumbers.size
        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                if ((ascending && sortedNumbers[j] > sortedNumbers[j + 1]) || (!ascending && sortedNumbers[j] < sortedNumbers[j + 1])) {
                    val temp = sortedNumbers[j]
                    sortedNumbers[j] = sortedNumbers[j + 1]
                    sortedNumbers[j + 1] = temp
                }
            }
        }
        return sortedNumbers
    }

    private fun saveSortingHistory(operation: String, result: List<Double>) {
        history.add(Sorting(operation, result))
    }

    fun printSortingHistory() {
        if (history.isEmpty()) {
            println("No history available.")
        } else {
            println("History:")
            history.forEachIndexed { index, sorting ->
                println("${index + 1}. ${sorting.sortingMethod} = ${sorting.result}")
            }
        }
    }
}