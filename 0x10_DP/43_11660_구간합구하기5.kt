package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val arr = Array(n + 1) { IntArray(n + 1) }
    for (i in 1 .. n) {
        st = StringTokenizer(br.readLine())
        for (j in 1 .. n) {
            arr[i][j] = st.nextToken().toInt()
        }
    }

    val input = Array(m) {
        st = StringTokenizer(br.readLine())
        IntArray(4) {
            st.nextToken().toInt()
        }
    }

    val dp = Array(n + 1) { IntArray(n + 1) }
    for (i in 1 .. n) {
        for (j in 1 .. n) {
            dp[i][j] = dp[i][j - 1] + dp[i - 1][j] + arr[i][j] - dp[i - 1][j - 1]
        }
    }

    val sb = StringBuilder()
    repeat(m) { i ->
        val (x1, y1, x2, y2) = input[i]
        sb.appendLine(dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1])
    }

    println(sb.toString())
}