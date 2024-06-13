package sortingMethod

import java.util.Scanner

fun main() {
    val sortingManager = SortingManager()
    val scanner = Scanner(System.`in`)

    while (true) {
        println("\nSorting Menu:")
        println("1. Bubble Sort Ascending")
        println("2. Bubble Sort Descending")
        println("3. Built In Descending")
        println("4. Built In Descending")
        println("5. Show History")
        println("6. Exit")
        print("Choose an option: ")

        when (scanner.nextInt()) {
            1 -> {
                print("Enter numbers separated by space: ")
                scanner.nextLine()
                val numbers = scanner.nextLine().split(" ").map { it.toDouble() }
                val result = sortingManager.sortingAscendingManual(numbers)
                println("Result: $result")
            }

            2 -> {
                print("Enter numbers separated by space: ")
                scanner.nextLine()
                val numbers = scanner.nextLine().split(" ").map { it.toDouble() }
                val result = sortingManager.sortingDescendingManual(numbers)
                println("Result: $result")
            }

            3 -> {
                print("Enter numbers separated by space: ")
                scanner.nextLine()
                val numbers = scanner.nextLine().split(" ").map { it.toDouble() }
                val result = sortingManager.sortingAscendingBuiltIn(numbers)
                println("Result: $result")
            }

            4 -> {
                print("Enter numbers separated by space: ")
                scanner.nextLine()
                val numbers = scanner.nextLine().split(" ").map { it.toDouble() }
                val result = sortingManager.sortingDescendingBuiltIn(numbers)
                println("Result: $result")
            }

            5 -> {
                sortingManager.printSortingHistory()
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
