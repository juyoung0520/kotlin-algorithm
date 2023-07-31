package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    val coins = IntArray(n) {
        br.readLine().toInt()
    }

    val dp = Array(n) {
        IntArray(k + 1) { j ->
            if (j == 0) {
                1
            } else {
                0
            }
        }
    }

    for (i in 1 until dp[0].size) {
        if (i >= coins[0] && i % coins[0] == 0) {
            dp[0][i] = 1
        }
    }

    for (i in 1 until dp.size) {
        for (j in 1 until dp[0].size) {
            if (j < coins[i]) {
                dp[i][j] = dp[i - 1][j] // 이전 조합에서 현재 값을 만드는 모든 경우
            } else {
                dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]] // 이전 조합과 현재 조합에서 현재 값을 만드는 모든 경우
            }
        }
    }

    println(dp[n - 1][k])
}