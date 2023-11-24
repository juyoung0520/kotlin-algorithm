package `kotlin-algorithm`.`0x0C_BackTracking`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.StringTokenizer

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private lateinit var garden: Array<IntArray>
private val yellows = ArrayList<IntArray>()
private lateinit var arr: Array<IntArray>
private var redOrGreen = 0
private var max = 0
private var flower = 2500

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val red = st.nextToken().toInt()
    val green = st.nextToken().toInt()

    garden = Array(n) { i ->
        st = StringTokenizer(br.readLine())
        IntArray(m) { j ->
            val tmp = st.nextToken().toInt()
            if (tmp == 2) {
                yellows.add(intArrayOf(i, j))
            }
            tmp
        }
    }

    arr = Array(red + green) { IntArray(2) }
    redOrGreen = red.coerceAtMost(green)

    comb(0, 0)

    println(max)
}

// 배양할 땅 조합 구하기
private fun comb(idx: Int, start: Int) {
    if (idx == arr.size) {
        comb2(0, 0)
        return
    }

    for (i in start until yellows.size) {
        arr[idx] = yellows[i]
        comb(idx + 1, i + 1)
    }
}

// 배양할 땅 중 배양액 색깔 적은 것의 위치 조합 구하기
private fun comb2(idx: Int, start: Int) {
    if (idx == redOrGreen) {
        val visited = Array(garden.size) {
            IntArray(garden[0].size)
        }

        val que = LinkedList<IntArray>()

        for (i in arr.indices) {
            que.add(arr[i])
            if (garden[arr[i][0]][arr[i][1]] == -2) {
                visited[arr[i][0]][arr[i][1]] = -1
            } else {
                visited[arr[i][0]][arr[i][1]] = 1 // 반대 색깔의 배양액
            }
        }

        var count = 0
        while (que.isNotEmpty()) {
            val (x, y) = que.poll()

            if (visited[x][y] == flower) continue // 꽃이 되었지만 그 전에 이미 큐에 들어갔을 경우 처리

            for (i in dx.indices) {
                val nx = x + dx[i]
                val ny = y + dy[i]

                if (nx !in garden.indices || ny !in garden[0].indices || garden[nx][ny] == 0 || visited[nx][ny] == flower) continue

                val v = if (visited[x][y] < 0) visited[x][y] - 1 else visited[x][y] + 1

                if (visited[nx][ny] == 0) {
                    visited[nx][ny] = v
                    que.add(intArrayOf(nx, ny))
                } else if (v + visited[nx][ny] == 0) { // 두 개의 색깔의 배양액이 동시에 도착
                    visited[nx][ny] = flower
                    count++
                }
            }
        }

        if (count > max) {
            max = count
        }
        return
    }

    for (i in start until arr.size) {
        garden[arr[i][0]][arr[i][1]] = -2 // 색깔 적은 배양액의 위치 표시
        comb2(idx + 1, i + 1)
        garden[arr[i][0]][arr[i][1]] = 2 // 되될리기
    }

}