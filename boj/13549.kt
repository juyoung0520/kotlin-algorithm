package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    if (n == k) {
        println(0)
        return
    }

    val dp = IntArray(100_001) { -1 }
    val dx = intArrayOf(-1, 1, 2)
    val que: Queue<Int> = LinkedList()
    que.add(n)
    dp[n] = 0

    while (que.isNotEmpty()) {
        val x = que.poll()

        for (i in dx.indices) {
            val xx = if (i == 2) x * dx[i] else x + dx[i]

            if (xx !in dp.indices || dp[xx] != -1) continue

            dp[xx] = dp[x]
            if (i != 2) {
                dp[xx]++
            }

            if (xx == k) {
                println(dp[xx])
                return
            }
            que.add(xx)
        }
    }

}