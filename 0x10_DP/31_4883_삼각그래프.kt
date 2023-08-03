package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()
    var tc = 1
    while (true) {
        val n = br.readLine().toInt()
        if (n == 0) {
            break
        }

        val nodes = Array(n) {
            val st = StringTokenizer(br.readLine())
            IntArray(3) {
                st.nextToken().toInt()
            }
        }

        val dp = Array(n) { IntArray(3) }
        dp[1][0] = nodes[0][1] + nodes[1][0]
        dp[1][1] = nodes[0][1].coerceAtMost(dp[1][0]).coerceAtMost(nodes[0][1] + nodes[0][2]) + nodes[1][1]
        dp[1][2] = nodes[0][1].coerceAtMost(dp[1][1]).coerceAtMost(nodes[0][1] + nodes[0][2]) + nodes[1][2]

        for (i in 2 until n) {
            dp[i][0] = dp[i - 1][0].coerceAtMost(dp[i - 1][1]) + nodes[i][0]
            dp[i][1] = dp[i - 1][0].coerceAtMost(dp[i - 1][1]).coerceAtMost(dp[i - 1][2]).coerceAtMost(dp[i][0]) + nodes[i][1]
            dp[i][2] = dp[i - 1][1].coerceAtMost(dp[i - 1][2]).coerceAtMost(dp[i][1]) + nodes[i][2]
        }

        sb.appendLine("$tc. ${dp[n - 1][1]}")
        tc++
    }

    println(sb.toString())
}