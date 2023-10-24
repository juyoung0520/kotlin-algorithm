package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val tc = br.readLine().toInt()
    val sb = StringBuilder()
    repeat(tc) {
        val n = br.readLine().toInt()
        val tmp = br.readLine().toCharArray()
        val nums = IntArray(n) {
            tmp[it] - '0'
        }
        val tmp2 = br.readLine().toCharArray()
        val bombs = BooleanArray(n) {
            tmp2[it] == '*'
        }

        if (n == 1) {
            sb.appendLine(1)
        } else {
            var count = 0
            for (i in nums.indices) {
                when (i) {
                    0 -> {
                        if (nums[i] != 0 && nums[i + 1] != 0) {
                            nums[i]--
                            nums[i + 1]--
                            count++
                        }
                    }
                    n - 1 -> {
                        if (nums[i - 1] != 0 && nums[i] != 0) {
                            nums[i - 1]--
                            nums[i]--
                            count++
                        }
                    }
                    else -> {
                        if (nums[i - 1] != 0 && nums[i] != 0 && nums[i + 1] != 0) {
                            nums[i - 1]--
                            nums[i]--
                            nums[i + 1]--
                            count++
                        }
                    }
                }
            }
            sb.appendLine(count)
        }
    }

    println(sb.toString())
}