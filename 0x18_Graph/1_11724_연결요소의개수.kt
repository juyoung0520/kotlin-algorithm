package `kotlin-algorithm`.`0x18_Graph`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

private lateinit var arr: Array<MutableList<Int>>
private lateinit var visited: BooleanArray
private var count = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    arr = Array(n) { mutableListOf() }
    visited = BooleanArray(n)

    repeat(m) {
        st = StringTokenizer(br.readLine())
        val num1 = st.nextToken().toInt() - 1
        val num2 = st.nextToken().toInt() - 1
        arr[num1].add(num2)
        arr[num2].add(num1)
    }

    for (i in arr.indices) {
        if (visited[i].not()) {
            dfs(i)
            count++
        }
    }

    println(count)
}

private fun dfs(idx: Int) {
    if (visited[idx]) {
        return
    }

    visited[idx] = true

    for (next in arr[idx]) {
        if (visited[next].not()) {
            dfs(next)
        }
    }
}