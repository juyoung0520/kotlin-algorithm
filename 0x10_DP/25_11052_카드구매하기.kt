package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val pay = Array(n + 1) {
        if (it == 0) {
            0
        } else {
            st.nextToken().toInt()
        }
    }
    val dp = IntArray(n + 1)

    dp[1] = pay[1]
    for (i in 2 until dp.size) {
        dp[i] = pay[i]
        for (j in 1 until i) {
            dp[i] = dp[i].coerceAtLeast(dp[j] + dp[i - j])
        }
    }

    println(dp[n])
}