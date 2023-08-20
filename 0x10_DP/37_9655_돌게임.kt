package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
    둘다 1만 선택해도 완벽한 게임을 한 결과이므로 홀수면 SY, 짝수면 CY
    dp로 하면 게임 턴 횟수를 통해 정답을 구할 수 있음
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val dp = IntArray(n + 1) // 게임이 끝나는 최소 턴 횟수 (최소는 기준일 뿐, 기준이 달라도 결과는 동일)

    dp[1] = 1
    if (dp.size >= 3) {
        dp[2] = 2
    }
    for (i in 3 .. n) {
        dp[i] = dp[i - 1].coerceAtMost(dp[i - 3]) + 1
    }
    println(if (dp[n] % 2 == 1) "SK" else "CY")
}