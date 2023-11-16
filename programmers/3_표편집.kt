package `kotlin-algorithm`.programmers

import java.util.*

fun main() {
    val n = 8
    val k = 2
    val cmd = arrayOf("D 2", "C", "U 3", "C", "Z")
    println(solution(n, k, cmd))
}

private fun solution(n: Int, k: Int, cmd: Array<String>): String {
    val sb = StringBuilder()
    val pre = IntArray(n)
    val next = IntArray(n)
    val cStack = Stack<Node81303>()
    var cursor = k

    for (i in 0 until n) {
        sb.append('O')
        pre[i] = i - 1
        next[i] = i + 1
    }
    next[n - 1] = -1

    for (c in cmd) {
        when (c[0]) {
            'D' -> {
                var count = c.substring(2).toInt()
                while (count-- > 0) {
                    cursor = next[cursor]
                }
            }

            'U' -> {
                var count = c[2] - '0'
                while (count-- > 0) {
                    cursor = pre[cursor]
                }
            }

            'C' -> {
                cStack.push(Node81303(pre[cursor], cursor, next[cursor]))
                // 처음이 아니면
                if (pre[cursor] != -1) next[pre[cursor]] = next[cursor]
                // 마지막이 아니면
                if (next[cursor] != -1) pre[next[cursor]] = pre[cursor]

                sb.setCharAt(cursor, 'X')
                cursor = if (next[cursor] != -1) {
                    next[cursor]
                } else {
                    pre[cursor]
                }
            }

            'Z' -> {
                val node = cStack.pop()
                if (node.pre != -1) next[node.pre] = node.cur
                if (node.nxt != -1) pre[node.nxt] = node.cur
                sb.setCharAt(node.cur, 'O')
            }
        }
    }
    return sb.toString()
}

data class Node81303(val pre: Int, val cur: Int, val nxt: Int)
