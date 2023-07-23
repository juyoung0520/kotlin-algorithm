package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val dx = intArrayOf(-1, 0, 0, 1)
private val dy = intArrayOf(0, -1, 1, 0)
private lateinit var shark: Node24
private lateinit var arr: Array<IntArray>
private var sharkSize = 2

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    var st: StringTokenizer
    var num: Int
    arr = Array(n) { i ->
        st = StringTokenizer(br.readLine())
        IntArray(n) { j ->
            num = st.nextToken().toInt()
            if (num == 9) {
                shark = Node24(i, j)
            }
            num
        }
    }
    arr[shark.x][shark.y] = 0 // 처음 상어 자리 초기화

    var count = 0
    var time = 0

    while (true) {
        val tmp = eatFish(n, arr)
        if (tmp == -1) {
            println(time)
            break
        }
        time += tmp // 돌아갈 수 있기 때문에 최적 경로로 구하면 안된다.
        if (++count == sharkSize) {
            sharkSize++
            count = 0
        }
    }
}

private fun eatFish(n: Int, arr: Array<IntArray>): Int {
    val que = LinkedList<Node24>()
    val distance = Array(n) { IntArray(n) { - 1} }
    val fishes = PriorityQueue<Node24>(compareBy ({ it.x }, { it.y })) // 거리 2 이상이면 큐에 담는 방향 순서 조절로 정할 수 없다. 
    que.add(shark)
    distance[shark.x][shark.y] = 0

    while (que.isNotEmpty()) {
        repeat(que.size) {
            val (x, y) = que.poll()
            for (i in dx.indices) {
                val xx = dx[i] + x
                val yy = dy[i] + y

                if (xx !in arr.indices || yy !in arr.indices || distance[xx][yy] != -1 || arr[xx][yy] > sharkSize) continue

                if (arr[xx][yy] != 0 && arr[xx][yy] < sharkSize) {
                    fishes.add(Node24(xx, yy))
                } else {
                    que.add(Node24(xx, yy))
                }
                distance[xx][yy] = distance[x][y] + 1
            }
        }
        if (fishes.isNotEmpty()) {
            val first = fishes.poll()
            arr[first.x][first.y] = 0
            shark = first
            return distance[first.x][first.y]
        }
    }

    return -1
}

data class Node24(val x: Int, val y: Int)