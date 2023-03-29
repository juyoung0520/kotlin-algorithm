// https://www.acmicpc.net/problem/14500

package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var arr: Array<IntArray>
private lateinit var visited: Array<BooleanArray>
private var max = Int.MIN_VALUE

private val dx = arrayOf(-1, 1, 0, 0)
private val dy = arrayOf(0, 0, -1, 1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    arr = Array(n) {
        st = StringTokenizer(br.readLine())
        IntArray(m) {
            st.nextToken().toInt()
        }
    }
    visited = Array(n) { BooleanArray(m) }

    for (i in arr.indices) {
        for (j in arr[0].indices) {
            visited[i][j] = true
            dfs(1, i, j, arr[i][j])
            visited[i][j] = false
        }
    }

    println(max)
}

private fun dfs(idx: Int, x: Int, y: Int, sum: Int) {
    if (idx == 4) {
        max = max.coerceAtLeast(sum)
        return
    }

    for (i in dx.indices) {
        val xx = x + dx[i]
        val yy = y + dy[i]

        if (xx !in arr.indices || yy !in arr[0].indices || visited[xx][yy]) continue

        visited[xx][yy] = true

        if (idx == 2) {
            dfs(idx + 1, x, y, sum + arr[xx][yy])
        }
        dfs(idx + 1, xx, yy, sum + arr[xx][yy])

        visited[xx][yy] = false
    }
}