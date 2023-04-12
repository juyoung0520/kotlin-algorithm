package `kotlin-algorithm`.programmers

import java.util.*
import kotlin.math.abs

private val dx = intArrayOf(1, 0, 0, -1)
private val dy = intArrayOf(0, -1, 1, 0)
private val dir = charArrayOf('d', 'l', 'r', 'u')
private var answer = ""
private var findAnswer = false

fun main() {
    println(solution(3, 4, 2, 3, 3, 1, 5))
}

private fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
    // 문제 성립 조건 판단: 최소 횟수가 K보다 작은지, K - 최소 횟수가 짝수인지(갔다 돌아와야 하니)
    val z = k - (abs(r - x) + abs(c - y))
    if (z < 0 || z % 2 != 0) return "impossible"

    dfs(0, x, y, "", n, m, r, c, k)

    return  if (findAnswer) answer else "impossible"
}

private fun dfs(idx: Int, a: Int, b: Int, s: String, n: Int, m: Int, r: Int, c: Int, k: Int) {
    if (findAnswer) return
    if (idx == k) {
        if (a == r && b == c) {
            answer = s
            findAnswer = true
        }
        return
    }

    for (i in dx.indices) {
        val aa = a + dx[i]
        val bb = b + dy[i]

        if (aa !in 1 .. n || bb !in 1 .. m) continue
        // 현재 부터 K - (idx + 1) 안에 갈 수 있는지 확인
        if (abs(r - aa) + abs(c - bb) + idx + 1 > k) continue

        dfs(idx + 1, aa, bb, s + dir[i], n, m, r, c, k)
    }
}

// 시간초과: 처음 도착이 정답이므로 모든 경우를 보면서 갈 필요가 없다.
private fun bfs(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
    val que: Queue<Node> = LinkedList()
    que.add(Node(x, y, ""))

    repeat(k) { time ->
        repeat(que.size) {
            val (a, b, s) = que.poll()

            for (i in dx.indices) {
                val aa = a + dx[i]
                val bb = b + dy[i]

                if (aa !in 1 .. n || bb !in 1 .. m) continue

                if (abs(aa - r) + abs(bb - c) + time + 1 > k) continue

                if (time == k - 1) {
                    if (aa == r && bb == c) return s + dir[i]
                }

                que.add(Node(aa, bb, s + dir[i]))
            }
        }
    }

    return "impossible"
}

private data class Node(val x: Int, val y: Int, val s: String)