package `kotlin-algorithm`.`0x18_Graph`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val arr = Array(n) { mutableListOf<Int>() }
    val visited = BooleanArray(n)
    var st: StringTokenizer
    repeat(m) {
        st = StringTokenizer(br.readLine())
        val n1 = st.nextToken().toInt() - 1
        val n2 = st.nextToken().toInt() - 1
        arr[n1].add(n2)
        arr[n2].add(n1)
    }

    val que: Queue<Int> = LinkedList()
    que.add(0)
    visited[0] = true

    var count = 0
    while (que.isNotEmpty()) {
        val idx = que.poll()
        for (next in arr[idx]) {
            if (visited[next].not()) {
                que.add(next)
                visited[next] = true
                count++
            }
        }
    }

    println(count)
}