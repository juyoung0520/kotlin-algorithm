package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    val MOD = 1000000000
    val dp = Array(k + 1) {
        IntArray(n + 1) { 1 }
    }

    for (i in 0 .. n) {
        dp[1][i] = 1
    }

    for (i in 2 .. k) {
        dp[i][0] = 1
    }

    for (i in 2 .. k) {
        for (j in 1 .. n) {
            dp[i][j] = dp[i][j - 1] % MOD + dp[i - 1][j] % MOD
        }
    }

    println(dp[k][n] % MOD)
}