package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val dp = IntArray(abs(n) + 1)
    val DIVIDE = 1000_000_000

    if (dp.size >= 2) {
        dp[1] = 1
    }

    if (n < 0) {
        for (i in 2 until dp.size) {
            dp[i] = dp[i - 2] % DIVIDE - dp[i - 1] % DIVIDE
        }
    } else {
        for (i in 2 until dp.size) {
            dp[i] = dp[i - 2] % DIVIDE + dp[i - 1] % DIVIDE
        }
    }

    val sb = StringBuilder()
    when {
        dp.last() > 0 -> sb.appendLine(1)
        dp.last() == 0 -> sb.appendLine(0)
        else -> sb.appendLine(-1)
    }
    sb.append(abs(dp.last()) % DIVIDE)
    println(sb.toString())
}