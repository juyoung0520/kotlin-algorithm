package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val s = br.readLine()
    val nums = DoubleArray(n) {
        br.readLine().toDouble()
    }
    val que = LinkedList<Double>()
    var ans = 0.0

    for (c in s) {
        if (c.isUpperCase().not()) {
            val n1 = que.pollLast()
            val n2 = que.pollLast()

            ans = when (c) {
                '*' -> n2 * n1
                '+' -> n2 + n1
                '-' -> n2 - n1
                else -> n2 / n1
            }

            que.add(ans)
        } else {
            que.add(nums[c - 'A'])
        }
    }

    println(String.format("%.2f", ans))
}