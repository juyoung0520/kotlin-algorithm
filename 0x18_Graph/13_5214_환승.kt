package `kotlin-algorithm`.`0x18_Graph`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val graph = Array(n + m + 1) { ArrayList<Int>() }

    for (i in 1 .. m) {
        st = StringTokenizer(br.readLine())
        for (j in 0 until k) {
            val node = st.nextToken().toInt()
            val trans = i + n // 환승역
            graph[trans].add(node)
            graph[node].add(trans)
        }
    }

    val que = LinkedList<Int>()
    val distance = IntArray(n + m + 1)
    distance[1] = 1
    que.add(1)

    while (que.isNotEmpty()) {
        val idx = que.poll()

        if (idx == n) {
            println(distance[idx])
            return
        }

        for (next in graph[idx]) {
            if (distance[next] != 0) continue

            // 환승역
            if (next > n) {
                distance[next] = distance[idx]
            } else {
                distance[next] = distance[idx] + 1
            }

            que.add(next)
        }
    }

    println(-1)
}