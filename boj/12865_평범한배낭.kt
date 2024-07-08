package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    val arr = Array(n) {
        st = StringTokenizer(br.readLine())
        IntArray(2) {
            st.nextToken().toInt()
        }
    }

    val dp = Array(n + 1) { IntArray(k + 1) }

    for (i in 1..n) {
        for (W in 1..k) {
            val w = arr[i - 1][0]
            val v = arr[i - 1][1]

            dp[i][W] = if (w <= W) {
                dp[i - 1][W].coerceAtLeast(dp[i - 1][W - w] + v)
            } else {
                dp[i - 1][W]
            }
        }
    }

    println(dp[n][k])
}