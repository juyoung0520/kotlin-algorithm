package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val vips = mutableSetOf<Int>()
    repeat(m) {
        val idx = br.readLine().toInt()
        vips.add(idx)
    }

    val dp = IntArray(n + 1)

    dp[0] = 1
    dp[1] = dp[0]
    for (i in 2 until dp.size) {
        if (vips.contains(i)) {
            dp[i] = dp[i - 1]
        } else if (vips.contains(i - 1)) {
            dp[i] = dp[i - 2]
        } else {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
    }

    println(dp[n])
}