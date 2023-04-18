package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt() // 코인 개수
    val k = st.nextToken().toInt() // 목표
    val coins = IntArray(n) {
        br.readLine().toInt()
    }
    val dp = IntArray(k + 1) { 10001 }
    dp[0] = 0

    for (i in coins.indices) {
        for (j in coins[i] until dp.size) {
           dp[j] = dp[j].coerceAtMost(dp[j - coins[i]] + 1)
        }
    }

    println(if (dp[k] == 10001) -1 else dp[k])
}