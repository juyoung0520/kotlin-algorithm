package `kotlin-algorithm`.`0x18_Graph`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*

private lateinit var graph: Array<ArrayList<Int>>
private lateinit var visited: IntArray

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val k = br.readLine().toInt()
    val sb = StringBuilder()

    for(t in 0 until k) {
        var st = StringTokenizer(br.readLine())
        val v = st.nextToken().toInt()
        val e = st.nextToken().toInt()
        graph = Array(v) {
            ArrayList<Int>()
        }

        for (i in 0 until e) {
            st = StringTokenizer(br.readLine())
            val v1 = st.nextToken().toInt() - 1
            val v2 = st.nextToken().toInt() - 1
            graph[v1].add(v2)
            graph[v2].add(v1)
        }

        visited = IntArray(v)
        var res = true

        for (i in 0 until v) {
            if (visited[i] == 0) {
                res = bfs(i)
                if (!res) {
                    break
                }
            }
        }

        if (res) {
            sb.appendLine("YES")
        } else {
            sb.appendLine("NO")
        }
    }

    print(sb.toString())
}

private fun bfs(start: Int): Boolean {
    val que: Queue<Int> = LinkedList()
    que.add(start)
    visited[start] = 1

    while (que.isNotEmpty()) {
        val node = que.poll()

        for (next in graph[node]) {
            if (visited[next] == 0) {
                que.add(next)
                visited[next] = -visited[node]
            } else if (visited[next] == visited[node]) {
                return false
            }
        }
    }
    return true
}