package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val dp = BooleanArray(1001) // true - SK 이기는 경우, false - CY 이기는 경우

    dp[1] = true
    dp[3] = true
    dp[4] = true

    for (i in 5 .. n) {
        if (!dp[i - 1] || !dp[i - 3] || !dp[i - 4]) {
            dp[i] = true
        }
    }

    println(if (dp[n]) "SK" else "CY")
}