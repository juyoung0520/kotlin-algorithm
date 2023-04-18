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
        val dp = IntArray(m + 1)

        for (i in coins.indices) {
            if (coins[i] < dp.size) {
                dp[coins[i]]++
            }
            for (j in coins[i] + 1 until dp.size) {
                dp[j] += dp[j - coins[i]]
            }
        }
        sb.appendLine(dp[m])
    }

    println(sb.toString())
}