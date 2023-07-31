package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val dp = IntArray(n + 1)
    dp[1] = 1
    if (n >= 2) {
        dp[2] = dp[1] + 1
    }

    for (i in 3 until dp.size) {
        dp[i] = dp[i - 1] % 15746 + dp[i - 2] % 15746
    }

    println(dp[n] % 15746)
}