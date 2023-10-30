package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val nums = LongArray(n) { // IntArray면 계산할 때 이미 overflow 날 수 있다.
        br.readLine().toLong()
    }

    nums.sort()

    var pivot = n
    for (i in nums.indices) {
        if (nums[i] > 0) {
            pivot = i
            break
        }
    }

    var sum = 0L
    var i = 0
    while (i < pivot) {
        if (i == pivot - 1) {
            sum += nums[i]
            i++
        } else {
            sum += nums[i] * nums[i + 1]
            i += 2
        }
    }

    i = n - 1
    while (i >= pivot) {
        if (i == pivot || nums[i] == 1L || nums[i - 1] == 1L) {
            sum += nums[i]
            i--
        } else {
            sum += nums[i] * nums[i - 1]
            i -= 2
        }
    }

    println(sum)
}