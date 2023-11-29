package `kotlin-algorithm`.`0x18_Graph`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*

/*
 플로이드 와셜 풀이
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val graph = Array(n) { i ->
        IntArray(n) { j ->
            if (i == j) {
                0
            } else {
                51
            }
        }
    }
    val scores = IntArray(n)
    var min = Int.MAX_VALUE

    while (true) {
        var st = StringTokenizer(br.readLine())
        val num1 = st.nextToken().toInt()
        val num2 = st.nextToken().toInt()

        if (num1 == -1 && num2 == -1) break

        graph[num1 - 1][num2 - 1] = 1
        graph[num2 - 1][num1 - 1] = 1
    }

    for (k in 0 until n) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                val tmp = graph[i][k] + graph[k][j]
                if (graph[i][j] > tmp) {
                    graph[i][j] = tmp
                }
            }
        }
    }

    for (i in 0 until n) {
        var score = 0
        for (j in 0 until n) {
            if (score < graph[i][j]) {
                score = graph[i][j]
            }
        }
        scores[i] = score
        min = min.coerceAtMost(score)
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