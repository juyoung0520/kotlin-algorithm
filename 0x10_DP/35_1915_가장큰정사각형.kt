package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    // 현재 인덱스를 오른쪽 아래 점으로 포함하는 가장 큰 정사각형의 한 변 길이
    val dp = Array(n) {
        val input = br.readLine()
        IntArray(m) { j ->
            input[j] - '0'
        }
    }

    for (i in 1 until n) {
        for (j in 1 until m) {
            if (dp[i][j] == 0) continue // 본인이 0이면 정사각형 될 수 없음

            dp[i][j] = dp[i - 1][j].coerceAtMost(dp[i][j - 1]).coerceAtMost(dp[i - 1][j - 1]) + 1
        }
    }

    var max = Int.MIN_VALUE
    for (i in dp.indices) {
        for (j in dp[0].indices) {
            if (dp[i][j] > max) {
                max = dp[i][j]
            }
        }
    }

    println(max * max)
}