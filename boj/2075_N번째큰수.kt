package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val arr = Array(n) {
        val st = StringTokenizer(br.readLine())
        IntArray(n) {
            st.nextToken().toInt()
        }
    }

    val positions = IntArray(n) { n - 1 } // 현재 인덱스들

    repeat (n) {
        var max = Int.MIN_VALUE
        var p = -1
        for (i in positions.indices) {
            if (positions[i] == -1) continue

            if (max < arr[positions[i]][i]) {
                max = arr[positions[i]][i]
                p = i
            }
        }

        if (it == n - 1) {
            println(max)
        }

        positions[p]--
    }
}