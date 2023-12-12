package `kotlin-algorithm`.`0x18_Graph`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/*
O(n(n + m))
약 40만
 */
private lateinit var visited: BooleanArray
private var count = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val graph1 = Array(n) {
        ArrayList<Int>()
    }
    val graph2 = Array(n) {
        ArrayList<Int>()
    }

    repeat(m) {
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt() - 1
        val b = st.nextToken().toInt() - 1
        graph1[a].add(b)
        graph2[b].add(a)
    }

    val half = n / 2
    var answer = 0

    for (i in 0 until n) {
        visited = BooleanArray(n)

        count = 0
        dfs(i, graph1)
        if (count - 1 > half) {
            answer++
            continue
        }

        count = 0
        visited.fill(false)
        dfs(i, graph2)
        if (count - 1 > half) {
            answer++
        }
    }

    println(answer)
}

private fun dfs(node: Int, graph: Array<ArrayList<Int>>) {
    if (visited[node]) {
        return
    }

    visited[node] = true
    count++

    for (next in graph[node]) {
        if (!visited[next]) {
            dfs(next, graph)
        }
    }
}