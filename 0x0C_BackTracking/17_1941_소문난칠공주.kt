package `kotlin-algorithm`.`0x0C_BackTracking`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var arr: Array<CharArray>
private val sevens = Array(7) { IntArray(2) }

private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private var answer = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    arr = Array(5) {
        br.readLine().toCharArray()
    }

    // 조합
    comb(0, 0, 0)

    println(answer)
}

private fun comb(idx: Int, start: Int, yCount: Int) {
    if (yCount >= 4) {
        return
    }

    if (idx == 7) {
        if (bfs()) {
            answer++
        }
        return
    }

    for (i in start until 25) {
        val x = i / 5
        val y = i % 5

        sevens[idx][0] = x
        sevens[idx][1] = y

        if (arr[x][y] == 'S') {
            comb(idx + 1, i + 1, yCount)
        } else {
            comb(idx + 1, i + 1,yCount + 1)
        }
    }
}

private fun bfs(): Boolean {
    val visited = Array(5) { BooleanArray(5)  { true } }

    for (s in sevens) {
        visited[s[0]][s[1]] = false
    }

    val que = LinkedList<IntArray>()
    que.add(sevens[0])
    visited[sevens[0][0]][sevens[0][1]] = true
    var count = 1

    while (que.isNotEmpty()) {
        val (x, y) = que.poll()

        for (i in dx.indices) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx !in arr.indices || ny !in arr[0].indices || visited[nx][ny]) continue

            que.add(intArrayOf(nx, ny))
            visited[nx][ny] = true
            count++
        }
    }

    return count == sevens.size
}
