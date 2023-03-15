package `kotlin-algorithm`.`0x18_Graph`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*

private lateinit var arr: Array<MutableList<Int>>
private lateinit var visited: BooleanArray
private val sb = StringBuilder()

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val start = st.nextToken().toInt() - 1
    arr = Array(n) { mutableListOf() }
    visited = BooleanArray(n)

    repeat(m) {
        st = StringTokenizer(br.readLine())
        val n1 = st.nextToken().toInt() - 1
        val n2 = st.nextToken().toInt() - 1
        arr[n1].add(n2)
        arr[n2].add(n1)
    }

    repeat(n) {
        arr[it].sort()
    }

    dfs(start)
    sb.appendLine()
    visited = BooleanArray(n)
    bfs(start)

    println(sb.toString())
}

private fun dfs(idx: Int) {
    if (visited[idx]) {
        return
    }

    visited[idx] = true
    sb.append("${idx + 1} ")

    for (next in arr[idx]) {
        if (visited[next].not()) {
            dfs(next)
        }
    }
}

private fun bfs(start: Int) {
    val que: Queue<Int> = LinkedList()
    que.add(start)
    visited[start] = true
    sb.append("${start + 1} ")

    while (que.isNotEmpty()) {
        val idx = que.poll()

        for (next in arr[idx]) {
            if (visited[next].not()) {
                que.add(next)
                visited[next] = true
                sb.append("${next + 1} ")
            }
        }
    }
}