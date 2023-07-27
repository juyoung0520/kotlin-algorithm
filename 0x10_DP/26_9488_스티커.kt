package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.StringTokenizer
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val tc = br.readLine().toInt()
    val sb = StringBuilder()

    repeat(tc) {
        val n = br.readLine().toInt()
        val arr = Array(2) {
            val st = StringTokenizer(br.readLine())
            IntArray(n) {
                st.nextToken().toInt()
            }
        }
        val dp = Array(2) {
            IntArray(n)
        }

        dp[0][0] = arr[0][0]
        dp[1][0] = arr[1][0]
        if (dp[0].size >= 2) {
            dp[0][1] = dp[1][0] + arr[0][1]
            dp[1][1] = dp[0][0] + arr[1][1]
        }

        for (i in 2 until dp[0].size) {
            dp[0][i] = max(dp[1][i - 1], dp[1][i - 2]) + arr[0][i]
            dp[1][i] = max(dp[0][i - 1], dp[0][i - 2]) + arr[1][i]
        }

        sb.appendLine(max(dp[0][n - 1], dp[1][n - 1]))
    }

    println(sb.toString())
}