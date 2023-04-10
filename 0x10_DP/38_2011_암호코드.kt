package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader

private var n = 0
private var num = ""
private val MOD = 1000_000

fun main() {
    num = BufferedReader(InputStreamReader(System.`in`)).readLine()
    n = num.length

    if (num[0] == '0') {
        println(0)
        return
    }

    val dp = IntArray(5001)
    dp[0] = 1
    dp[1] = 1

    for (i in 2 .. n) {
        if (num[i - 1] != '0') {
            dp[i] = dp[i - 1] % MOD
        }

        val tmp = (num[i - 1] - '0') + (num[i - 2] - '0') * 10

        if (tmp in 10 .. 26) {
            dp[i] = (dp[i] + dp[i - 2]) % MOD
        }
    }

    println(dp[n])
}