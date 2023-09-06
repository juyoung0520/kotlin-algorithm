package `kotlin-algorithm`.ndb

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val c = st.nextToken().toInt()
    val arr = Array(n + 1) {
        ArrayList<Node>(n)
    }
    repeat(m) {
        st = StringTokenizer(br.readLine())
        val s = st.nextToken().toInt()
        val e = st.nextToken().toInt()
        val z = st.nextToken().toInt()
        arr[s].add(Node(z, e))
    }

    val nodes = IntArray(n + 1) {
        if (it == c) {
            0
        } else {
            Int.MAX_VALUE
        }
    }

    val que = PriorityQueue<Node>(compareBy { it.d })
    que.add(Node(nodes[c], c))

    while (que.isNotEmpty()) {
        val (d, now) = que.poll()

        if (nodes[now] < d) continue // 이미 방문

        for (next in arr[now]) {
            if (d + next.d < nodes[next.idx]) {
                nodes[next.idx] = d + next.d
                que.add(Node(nodes[next.idx], next.idx))
            }
        }
    }

    var max  = 0
    var count = 0
    for (i in 1 until nodes.size) {
        if (nodes[i] != Int.MAX_VALUE) {
            count++
            if (nodes[i] > max) {
                max = nodes[i]
            }
        }
    }
    println("${count - 1} $max")
}

data class Node(val d: Int, val idx: Int)