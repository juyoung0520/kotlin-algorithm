package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.PriorityQueue

private val heap = IntArray(100_000) {
    Int.MAX_VALUE
}
private var size = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val sb = StringBuilder()
    repeat(n) {
        val num = br.readLine().toInt()
        if (num != 0) {
            insert(num)
        } else {
            sb.appendLine(poll())
        }
    }
    println(sb.toString())
}

private fun solution1() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val que = PriorityQueue<Int>()
    val sb = StringBuilder()
    repeat(n) {
        val num = br.readLine().toInt()
        if (num != 0) {
            que.add(num)
        } else {
            sb.appendLine(if (que.isEmpty()) 0 else que.poll())
        }
    }
    println(sb.toString())
}

private fun insert(value: Int) {
    heap[++size] = value
    var i = size
    while (i > 1) {
        if (heap[i] < heap[i / 2]) {
            swap(i, i / 2)
        } else {
            break
        }
        i /= 2
    }
}

private fun poll(): Int {
    if (size == 0) return 0

    val min = heap[1]
    heap[1] = heap[size]
    heap[size--] = Int.MAX_VALUE

    var i = 1
    while (i * 2 <= size) { // 자식이 하나일 수도 있음
        if (heap[i] < heap[i * 2] && heap[i] < heap[i * 2 + 1]) break

        if (heap[i * 2] < heap[i * 2 + 1]) {
            swap(i, i * 2)
            i *= 2
        } else {
            swap(i, i * 2 + 1)
            i = i * 2 + 1
        }
    }

    return min
}

private fun swap(i1: Int, i2: Int) {
    val tmp = heap[i1]
    heap[i1] = heap[i2]
    heap[i2] = tmp
}