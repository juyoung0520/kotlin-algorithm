package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private lateinit var map: Array<IntArray>
private lateinit var dp: Array<IntArray>
private var m = 0
private var n = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    m = st.nextToken().toInt()
    n = st.nextToken().toInt()
    map = Array(m) {
        st = StringTokenizer(br.readLine())
        IntArray(n) {
            st.nextToken().toInt()
        }
    }
    dp = Array(m) {
        IntArray(n) {
            -1
        }
    }

    println(dfs(0, 0))
}

private fun dfs(x: Int, y: Int): Int {
    if (x == m - 1 && y == n - 1) {
        return 1
    }

    if (dp[x][y] != -1) {
        return dp[x][y]
    }

    dp[x][y] = 0 // 방문 처리

    for (i in dx.indices) {
        val xx = x + dx[i]
        val yy = y + dy[i]

        if (xx !in map.indices || yy !in map[0].indices || map[x][y] <= map[xx][yy]) continue

        dp[x][y] += dfs(xx, yy)
    }

    return dp[x][y]
}