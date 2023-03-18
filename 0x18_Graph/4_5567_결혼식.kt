package `kotlin-algorithm`.`0x18_Graph`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val friends = Array(n) { mutableListOf<Int>() }
    val visited = BooleanArray(n)

    repeat(m) {
        val st = StringTokenizer(br.readLine())
        val num1 = st.nextToken().toInt() - 1
        val num2 = st.nextToken().toInt() - 1
        friends[num1].add(num2)
        friends[num2].add(num1)
    }

    val que: Queue<Int> = LinkedList()
    que.add(0)
    visited[0] = true

    var count = 0
    repeat(2) {
        repeat(que.size) {
            val idx = que.poll()

            for (next in friends[idx]) {
                if (visited[next].not()) {
                    que.add(next)
                    visited[next] = true
                    count++
                }
            }
        }
    }

    println(count)
}