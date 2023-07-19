package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val wines = IntArray(n + 1) {
        if (it == 0) {
            0
        } else {
            br.readLine().toInt()
        }
    }

    val dp = IntArray(n + 1)
    dp[1] = wines[1]
    if (n >= 2) {
        dp[2] = wines[1] + wines[2]
    }
    for (i in 3 until  dp.size) {
        dp[i] = dp[i - 1].coerceAtLeast(dp[i - 2].coerceAtLeast(dp[i - 3] + wines[i - 1]) + wines[i])
    }

    println(dp[n])
}