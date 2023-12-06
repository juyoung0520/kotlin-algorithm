package `kotlin-algorithm`.`0x18_Graph`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

/*
O(n + m)
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val graph = Array(n) {
        ArrayList<Int>()
    }

    repeat(m) {
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt() - 1
        val b = st.nextToken().toInt() - 1
        graph[a].add(b)
        graph[b].add(a)
    }

    val visited = BooleanArray(n)
    val que: Queue<Int> = LinkedList()
    que.add(0)
    visited[0] = true

    var minIdx = n
    var dis = -1
    var count = 0

    while (que.isNotEmpty()) {
        minIdx = n
        dis++
        count = que.size
        repeat(que.size) {
            val node = que.poll()
            minIdx = minIdx.coerceAtMost(node)

            for (next in graph[node]) {
                if (!visited[next]) {
                    visited[next] = true
                    que.add(next)
                }
            }
        }
    }

    println("${minIdx + 1} $dis $count")
}