package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val tc = br.readLine().toInt()
    val sb = StringBuilder()
    var max = Int.MIN_VALUE
    val inputs = IntArray(tc) {
        val n = br.readLine().toInt()
        if (n > max) {
            max = n
        }
        n
    }
    val MOD = 1_000_000_009
    val dp = LongArray(max + 1)
    dp[0] = 1L
    dp[1] = dp[0]
    if (dp.size > 2) {
        dp[2] = dp[1] + dp[0]
    }

    for (i in 3 until dp.size) {
        dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD // 3개의 10 9승 + 9 미만의 값을 더하는데 이 값이 int를 넘어 설수 있다.
    }

    inputs.forEach {
        sb.appendLine(dp[it])
    }
    println(sb.toString())
}