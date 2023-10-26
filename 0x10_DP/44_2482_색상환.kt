package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val k = br.readLine().toInt()
    val MOD = 1_000_000_003

    val dp = Array(n + 1) {
        IntArray(k + 1)
    }

    for (i in dp.indices) {
        dp[i][1] = i
    }

    for (i in 2 .. n) {
        for (j in 2 .. k) {
            if (i < j * 2) continue
            dp[i][j] = dp[i - 2][j - 1] % MOD + dp[i - 1][j] % MOD
        }
    }

    println(dp[n][k] % MOD)
}