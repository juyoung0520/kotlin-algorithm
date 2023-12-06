package `kotlin-algorithm`.`0x18_Graph`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList

/*
n^2 + nm 으로 O(nm)
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
        val n1 = st.nextToken().toInt() - 1
        val n2 = st.nextToken().toInt() - 1
        graph[n2].add(n1)
    }

    var max = 0
    val res = IntArray(n)

    for (i in graph.indices) {
        var tmp = 0
        val visited = BooleanArray(n)
        val que: Queue<Int> = LinkedList()
        que.add(i)
        visited[i] = true

        while (que.isNotEmpty()) {
            val node = que.poll()

            for (next in graph[node]) {
                if (visited[next].not()) {
                    visited[next] = true
                    que.add(next)
                    tmp++
                }
            }
        }

        if (tmp > max) {
            max = tmp
        }
        res[i] = tmp
    }

    val sb = StringBuilder()
    for (i in res.indices) {
        if (max == res[i]) {
            sb.append("${i + 1} ")
        }
    }

    println(sb.toString())
}