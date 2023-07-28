package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val dp = Array(n + 1) {
        LongArray(10)
    }

    for (i in dp[1].indices) {
        dp[1][i] = 1
    }

    for (i in 2 until dp.size) {
        for (j in dp[i].indices) {
            if (j == 0) {
                dp[i][j] = dp[i - 1][j]
            } else {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % 10_007
            }
        }
    }

    var answer = 0L
    for (i in dp[n].indices) {
        answer += dp[n][i]
    }

    println(answer % 10_007)
}