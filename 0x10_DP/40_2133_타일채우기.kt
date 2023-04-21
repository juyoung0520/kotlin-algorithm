package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val dp = IntArray(31)
    dp[0] = 1 // dp[i - j]가 0이면 안되므로
    dp[2] = 3

    for (i in 4 .. n) {
        if (i % 2 == 0) {
            dp[i] = dp[i - 2] * 3
            var j = 4 // 4 이상 짝수 별로 2개씩 추가 모양 존재
            while (j <= i) {
                dp[i] += dp[i - j] * 2
                j += 2
            }
        }
    }

    println(dp[n])
}