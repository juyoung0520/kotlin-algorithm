package `kotlin-algorithm`.ndb

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/*
    1 ~ k 까지가 최단거리고,
    k ~ x 까지도 최단거리여야 함
    따라서 플로이드 워셜 알고리즘 사용
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val dp = Array(n + 1) { i ->
        IntArray(n + 1) { j ->
            if (i == j) {
                0
            } else {
                201 // overflow 주의
            }
        }
    }
    repeat(m) {
        st = StringTokenizer(br.readLine())
        val s = st.nextToken().toInt()
        val e = st.nextToken().toInt()
        dp[s][e] = 1
        dp[e][s] = 1
    }
    st = StringTokenizer(br.readLine())
    val x = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    // 방문 노드
    for (node in 1 .. n) {
        for (i in 1 .. n) {
            if (i == node) continue
            for (j in 1 .. n) {
                if (j == node) continue
                dp[i][j] = dp[i][j].coerceAtMost(dp[i][node] + dp[node][j])
            }
        }
    }

    val res = dp[1][k] + dp[k][x]
    println(if (res == 201) -1 else res)
}