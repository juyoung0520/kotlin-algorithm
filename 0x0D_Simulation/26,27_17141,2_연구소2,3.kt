package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var arr: Array<IntArray>
private val possibleList = mutableListOf<Node26>()
private lateinit var startList: Array<Node26?>
private var m = 0
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private var answer = Int.MAX_VALUE

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    m = st.nextToken().toInt()
    startList = Array(m) {
        null
    }

    arr = Array(n) { i ->
        st = StringTokenizer(br.readLine())
        IntArray(n) { j ->
            val d = st.nextToken().toInt()
            if (d == 2) {
                possibleList.add(Node26(i, j))
            }
            d
        }
    }

    combination(0, 0)

    println(if (answer == Int.MAX_VALUE) -1 else answer)
}

private fun combination(idx: Int, start: Int) {
    if (idx == m) {
        val que = LinkedList<Node26>()
        val tmp = Array(arr.size) { i ->
            IntArray(arr.size) { j ->
                if (arr[i][j] == 1) {
                    -2// 벽인 경우
                } else {
                    -1 // 방문 안한 빈칸
                }
            }
        }
        for (s in startList) {
            que.add(s!!)
            tmp[s.x][s.y] = 0
        }

        while (que.isNotEmpty()) {
            repeat(que.size) {
                val (x, y) = que.poll()
                for (i in dx.indices) {
                    val xx = x + dx[i]
                    val yy = y + dy[i]

                    if (xx !in tmp.indices || yy !in tmp.indices || tmp[xx][yy] == -2 || tmp[xx][yy] != -1) continue

                    tmp[xx][yy] = tmp[x][y] + 1
                    que.add(Node26(xx, yy))
                }
            }
        }

        var time = 0
        for (i in tmp.indices) {
            for (j in tmp.indices) {
                // 연구소 3 일 때 조건 추가
                // if (arr[i][j] == 2) continue
                if (tmp[i][j] == -1) return
                if (tmp[i][j] > time) {
                    time = tmp[i][j]
                }
            }
        }

        if (time < answer) {
            answer = time
        }
        return
    }

    for (i in start until possibleList.size) {
        startList[idx] = possibleList[i]
        combination(idx + 1, i + 1)
    }
}

data class Node26(val x: Int, val y: Int)