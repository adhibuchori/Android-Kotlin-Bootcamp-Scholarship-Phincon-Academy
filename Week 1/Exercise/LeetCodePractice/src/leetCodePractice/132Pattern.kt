package leetCodePractice

import java.util.*

fun find132pattern(nums: IntArray): Boolean {

    val stack = Stack<Int>()
    var k = Int.MIN_VALUE

    for (i in nums.indices.reversed()) {
        if (nums[i] < k) {
            return true
        }

        while (stack.isNotEmpty() && nums[i] > stack.peek()) {
            k = stack.pop()
        }

        stack.push(nums[i])
    }

    return false
}

fun main() {
    val nums = intArrayOf(1, 2, 3, 4)
    println(find132pattern(nums))

    val nums2 = intArrayOf(3, 1, 4, 2) // True
    println(find132pattern(nums2))
}