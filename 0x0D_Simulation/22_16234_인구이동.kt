package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import java.util.*
import kotlin.math.abs

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private lateinit var nations: Array<IntArray>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val l = st.nextToken().toInt()
    val r = st.nextToken().toInt()
    nations = Array(n) {
        st = StringTokenizer(br.readLine())
        IntArray(n) {
            st.nextToken().toInt()
        }
    }

    var day = 0
    while (day < 2000) {
        val visited = Array(n) { BooleanArray(n) }
        var count = 0 // 연합 있는지 확인

        for (i in nations.indices) {
            for (j in nations[0].indices) {
                if (!visited[i][j]) {
                    count += bfs(l, r, Node22(i, j), visited)
                }
            }
        }

        if (count == 0) {
            break
        }

        day++
    }

    println(day)
}

private fun bfs(l: Int, r: Int, start: Node22, visited: Array<BooleanArray>): Int {
    val que: Queue<Node22> = LinkedList()
    val unions = LinkedList<Node22>()
    que.add(start)
    unions.add(start)
    visited[start.x][start.y] = true
    var total = nations[start.x][start.y]
    var count = 1

    while (que.isNotEmpty()) {
        val (x, y) = que.poll()

        for (i in dx.indices) {
            val xx = x + dx[i]
            val yy = y + dy[i]

            if (xx !in nations.indices || yy !in nations[0].indices || visited[xx][yy] || abs(nations[x][y] - nations[xx][yy]) !in l .. r) continue

            val node = Node22(xx, yy)
            que.add(node)
            unions.add(node)
            visited[xx][yy] = true
            total += nations[xx][yy]
            count++
        }
    }

    val num = total / count
    for ((x, y) in unions) {
        nations[x][y] = num
    }

    return count - 1 // 자기 자신은 제외
}

data class Node22(val x: Int, val y: Int)