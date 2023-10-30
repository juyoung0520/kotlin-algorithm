package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val boxes = IntArray(n) {
        st.nextToken().toInt()
    }

    val dp = IntArray(n) { 1 }
    for (i in 1 until n) {
        for (j in 0 until i) {
            if(boxes[j] < boxes[i]) {
                dp[i] = dp[i].coerceAtLeast(dp[j] + 1)
            }
        }
    }

    var max = 0
    for (i in dp.indices) {
        if (dp[i] > max) {
            max = dp[i]
        }
    }

    println(max)
}