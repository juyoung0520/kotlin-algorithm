package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val d = st.nextToken().toInt()
    val graph = Array(d + 1) {
        if (it == d) mutableListOf()
        else mutableListOf(Node1446(it + 1, 1))
    }
    for (i in 0 until n) {
        st = StringTokenizer(br.readLine())
        val s = st.nextToken().toInt()
        val e = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        if (e in graph.indices) {
            graph[s].add(Node1446(e, v))
        }
    }

    val dist = IntArray(d + 1) { 10001 }
    dist[0] = 0
    val pq = PriorityQueue<Node1446>(compareBy { it.value }) // 현재에서 가중치 가장 작은 노드 선택
    pq.add(Node1446(0, 0))

    while (pq.isNotEmpty()) {
        val (e, current) = pq.poll()

        if (dist[e] < current) continue

        graph[e].forEach {
            val sum = current + it.value
            if (sum < dist[it.end]) {
                dist[it.end] = sum // 가장 빠른 경로 갱신
                pq.add(Node1446(it.end, sum))
            }
        }
    }

    var min = 10001
    for (i in 1 until dist.size) {
        val tmp = dist[i] + d - i
        if (tmp < min) {
            min = tmp
        }
    }

    println(min)
}

data class Node1446(val end: Int, val value: Int)