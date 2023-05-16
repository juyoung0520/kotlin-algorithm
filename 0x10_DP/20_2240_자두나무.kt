package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val t = st.nextToken().toInt()
    val w = st.nextToken().toInt()
    val trees = IntArray(t + 1) {
        if (it == 0) 0 else br.readLine().toInt() - 1
    }

    val dp = Array(2) {
        // 이전 인덱스 참고하기 때문에 하나 크게
        Array(t + 1) {
            IntArray(w + 2)
        }
    }

    for (i in 1 .. t) {
        // 이동 0회도 고려하는데, 1부터 시작이므로 w + 1까지
        for (j in 1 .. w + 1) {
            if (trees[i] == 0) {
                dp[0][i][j] = max(dp[0][i - 1][j] + 1, dp[1][i - 1][j - 1] + 1) // i초에 자두 떨어저서 + 1
                dp[1][i][j] = max(dp[0][i - 1][j - 1], dp[1][i - 1][j])
            } else {
                // 1초에 2나무로 바로 이동할 수는 없다.
                if (i == 1 && j == 1) continue

                dp[0][i][j] = max(dp[0][i - 1][j], dp[1][i - 1][j - 1])
                dp[1][i][j] = max(dp[0][i - 1][j - 1] + 1, dp[1][i - 1][j] + 1)
            }
        }
    }

    var max = Int.MIN_VALUE
    for (i in 1 .. w + 1) {
        max = max.coerceAtLeast(max(dp[0][t][i], dp[1][t][i]))
    }

    println(max)
}