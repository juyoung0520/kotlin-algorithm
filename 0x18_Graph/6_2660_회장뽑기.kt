package `kotlin-algorithm`.`0x18_Graph`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val graph = Array(n) {
        ArrayList<Int>()
    }
    val scores = IntArray(n)
    var min = Int.MAX_VALUE

    while (true) {
        var st = StringTokenizer(br.readLine())
        val num1 = st.nextToken().toInt()
        val num2 = st.nextToken().toInt()

        if (num1 == -1 && num2 == -1) break

        graph[num1 - 1].add(num2 - 1)
        graph[num2 - 1].add(num1 - 1)
    }

    for (i in 0 until n) {
        if (graph[i].size == n - 1) {
            scores[i] = 1
            min = 1
            continue
        }

        val visited = BooleanArray(n)
        val que = LinkedList<Int>()
        que.add(i)
        visited[i] = true
        var depth = -1

        while (que.isNotEmpty()) {
            depth++
            repeat(que.size) {
                val node = que.poll()

                for (next in graph[node]) {
                    if (visited[next].not()) {
                        que.add(next)
                        visited[next] = true
                    }
                }
            }
        }

        scores[i] = depth
        if (depth < min) {
            min = depth
        }
    }

    var count = 0
    val sb1 = StringBuilder("$min ")
    val sb2 = StringBuilder()
    for (i in scores.indices) {
        if (scores[i] == min) {
            count++
            sb2.append("${i + 1} ")
        }
    }
    sb1.append(count)
    println(sb1.toString())
    println(sb2.toString())
}