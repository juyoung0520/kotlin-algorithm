package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val tc = br.readLine().toInt()
    val sb = StringBuilder()
    repeat(tc) {
        val n = br.readLine().toInt()
        val st = StringTokenizer(br.readLine())
        val coins = IntArray(n) {
            st.nextToken().toInt()
        }
        val m = br.readLine().toInt()
        val dp = Array(n + 1) {
            IntArray(m + 1) { j ->
                if (j == 0) 1 else 0
            }
        }

        for (i in 1 .. n) {
            for (j in 1 .. m) {
                dp[i][j] = dp[i - 1][j]
                if (j >= coins[i - 1]) {
                    dp[i][j] += dp[i][j - coins[i - 1]]
                }
            }
        }

        sb.appendLine(dp[n][m])
    }

    println(sb.toString())
}