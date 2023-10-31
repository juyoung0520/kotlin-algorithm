package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val cards = IntArray(n) {
        br.readLine().toInt()
    }

    if (n == 1) {
        println(0)
        return
    }

    val que = PriorityQueue<Int>() // 매 번 제일 작은 카드 묶음 두 개 선택
    for (c in cards) {
        que.add(c)
    }

    var sum = 0
    while (que.size >= 2) {
        val n1 = que.poll()
        val n2 = que.poll()

        val tmp = n1 + n2
        sum += tmp

        que.add(tmp)
    }

    println(sum)
}