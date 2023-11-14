package `kotlin-algorithm`.programmers

import java.util.*

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private lateinit var arr: Array<CharArray>

fun main() {
    val places = arrayOf(
        arrayOf("POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"),
        arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"),
        arrayOf("PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"),
        arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"),
        arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP")
    )

    println(solution(places).contentToString())
}

private fun solution(places: Array<Array<String>>): IntArray {
    val answer: IntArray = IntArray(places.size)

    for (k in places.indices) {
        val p = places[k]
        arr = Array(p.size) {
            p[it].toCharArray()
        }

        if (checkPlace()) {
            answer[k] = 1
        }
    }

    return answer
}

private fun checkPlace(): Boolean {
    for (i in arr.indices) {
        for (j in arr.indices) {
            if (arr[i][j] == 'P') {
                if (bfs(i, j).not()) return false
            }
        }
    }

    return true
}

private fun bfs(i: Int, j: Int): Boolean {
    val que = LinkedList<Node81302>()
    val visited = Array(arr.size) {
        BooleanArray(arr.size)
    }
    que.add(Node81302(i, j))
    visited[i][j] = true

    repeat(2) {
        repeat(que.size) {
            val (x, y) = que.poll()

            for (d in dx.indices) {
                val nx = x + dx[d]
                val ny = y + dy[d]

                if (nx !in arr.indices || ny !in arr.indices || arr[nx][ny] == 'X' || visited[nx][ny]) continue
                if (Math.abs(nx - i) + Math.abs(ny - j) > 2) continue

                if (arr[nx][ny] == 'P') return false

                que.add(Node81302(nx, ny))
                visited[nx][ny] = true
            }
        }
    }

    return true
}



data class Node81302(val x: Int, val y: Int)