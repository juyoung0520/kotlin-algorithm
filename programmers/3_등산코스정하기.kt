package `kotlin-algorithm`.programmers

import java.util.*

fun main() {
    val n = 5
    val paths = arrayOf(intArrayOf(1, 2, 3), intArrayOf(2, 3, 5), intArrayOf(2, 4, 2), intArrayOf(2, 5, 4), intArrayOf(
        3, 4, 4), intArrayOf(4, 5, 3), intArrayOf(4, 6, 1), intArrayOf(5, 6, 1))
    val gates = intArrayOf(1, 3)
    val summits = intArrayOf(1, 3)

    val result = solution(n, paths, gates, summits)
    println(result)
}

private fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
    val graph = Array(n + 1) {
        mutableListOf<Node118669>()
    }

    val gateSet = gates.toSet()
    val summitSet = summits.toSet()

    for (p in paths) {
        val (s, e, w) = p

        when {
            gateSet.contains(s) || summitSet.contains(e) -> {
                graph[s].add(Node118669(e, w))
            }
            gateSet.contains(e) || summitSet.contains(s) -> {
                graph[e].add(Node118669(s, w))
            }
            else -> {
                graph[s].add(Node118669(e, w))
                graph[e].add(Node118669(s, w))
            }
        }
    }

    var summit = n
    var min = Int.MAX_VALUE

    val intensity = IntArray(n + 1) { 10000000 }
    val que = PriorityQueue<Node118669>(compareBy { it.weight })

    for (g in gates) {
        intensity[g] = 0
        que.add(Node118669(g, 0))
    }

    while (que.isNotEmpty()) {
        val (e, current) = que.poll() // 새로 선택된 지점과 현재 경로 중 최대값

        if (intensity[e] < current) continue

        for (next in graph[e]) {
            val tmp = current.coerceAtLeast(next.weight)
            if (tmp < intensity[next.end]) {
                intensity[next.end] = tmp
                que.add(Node118669(next.end, tmp))
            }
        }
    }

    for (s in summits) {
        if (intensity[s] < min) {
            summit = s
            min = intensity[s]
        } else if (intensity[s] == min) {
            summit = summit.coerceAtMost(s)
        }
    }

    return intArrayOf(summit, min)
}

private data class Node118669(val end: Int, val weight: Int)