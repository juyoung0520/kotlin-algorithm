package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.StringTokenizer

private var r = 0
private var c = 0
private lateinit var dusts: Array<IntArray>
private var cleanerTop: Node17144? = null
private var cleanerBottom: Node17144? = null
private val dx = intArrayOf(0, -1, 0, 1)
private val dy = intArrayOf(1, 0, -1, 0)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    r = st.nextToken().toInt()
    c = st.nextToken().toInt()
    var t = st.nextToken().toInt()
    val que = LinkedList<Node17144>()

    dusts = Array(r) { i ->
        st = StringTokenizer(br.readLine())
        IntArray(c) { j ->
            val num = st.nextToken().toInt()
            if (num == -1 && cleanerTop == null) {
                cleanerTop = Node17144(i, j)
                cleanerBottom = Node17144(i + 1, j)
            } else if (num >= 5) {
                que.add(Node17144(i, j))
            }
            num
        }
    }

    while (t-- > 0) {
        val arr = Array(r) { IntArray(c) }
        repeat(que.size) {
            // 미세먼지 확산
            val (x, y) = que.poll()
            for (i in dx.indices) {
                val nx = x + dx[i]
                val ny = y + dy[i]

                if (nx !in dusts.indices || ny !in dusts[0].indices || dusts[nx][ny] == -1) continue

                val diffusion = dusts[x][y] / 5
                arr[nx][ny] += diffusion
                arr[x][y] -= diffusion
            }
        }

        for (i in dusts.indices) {
            for (j in dusts[0].indices) {
                dusts[i][j] += arr[i][j]
            }
        }

        doAirCleanTop()
        doAirCleanBottom()

        for (i in dusts.indices) {
            for (j in dusts[0].indices) {
                if (dusts[i][j] >= 5) {
                    que.add(Node17144(i, j))
                }
            }
        }
    }

    var sum = 0
    for (i in dusts.indices) {
        for (j in dusts[0].indices) {
            if (dusts[i][j] != -1) {
                sum += dusts[i][j]
            }
        }
    }

    println(sum)
}

private fun doAirCleanTop() {
    val start = cleanerTop!!

    for (i in start.x - 1 downTo 1) {
        dusts[i][0] = dusts[i - 1][0]
    }

    for (i in 0 until c - 1) {
        dusts[0][i] = dusts[0][i + 1]
    }

    for (i in 0 until start.x) {
        dusts[i][c - 1] = dusts[i + 1][c - 1]
    }

    for (i in c - 1 downTo start.y + 2) {
        dusts[start.x][i] = dusts[start.x][i - 1]
    }
    dusts[start.x][start.y + 1] = 0
}

private fun doAirCleanBottom() {
    val start = cleanerBottom!!

    for (i in start.x + 1 until r - 1) {
        dusts[i][0] = dusts[i + 1][0]
    }

    for (i in 0 until c - 1) {
        dusts[r - 1][i] = dusts[r - 1][i + 1]
    }

    for (i in r - 1 downTo start.x + 1) {
        dusts[i][c - 1] = dusts[i - 1][c - 1]
    }

    for (i in c - 1 downTo start.y + 2) {
        dusts[start.x][i] = dusts[start.x][i - 1]
    }
    dusts[start.x][start.y + 1] = 0
}

data class Node17144(val x: Int, val y: Int)