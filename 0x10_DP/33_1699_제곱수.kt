package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val dp = IntArray(n + 1)

    for (i in 1 until dp.size) {
        dp[i] = i
        var j = 1
        while (j * j <= i) {
            dp[i] = dp[i].coerceAtMost(dp[i - j * j] + 1)
            j++
        }
    }

    println(dp[n])
}