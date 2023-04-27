package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.Deque
import java.util.LinkedList
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val str = br.readLine().toString()
    val m = br.readLine().toInt()

    val left: Deque<Char> = LinkedList()
    val right: Deque<Char> = LinkedList()
    for (c in str) {
        left.add(c)
    }

    repeat(m) {
        val st = StringTokenizer(br.readLine())
        val op = st.nextToken()
        when (op[0]) {
            'L' -> {
                if (left.isNotEmpty()) {
                    right.addFirst(left.pollLast())
                }
            }
            'D' -> {
                if (right.isNotEmpty()) {
                    left.add(right.poll())
                }
            }
            'B' -> {
                if (left.isNotEmpty()) {
                    left.pollLast()
                }
            }
            'P' -> {
                val c = st.nextToken()[0]
                left.add(c)
            }
        }
    }

    val sb = StringBuilder()
    for (c in left) {
        sb.append(c)
    }
    for (c in right) {
        sb.append(c)
    }

    println(sb.toString())
}