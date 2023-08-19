package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    var st = StringTokenizer(br.readLine())
    val nums = IntArray(n) {
        st.nextToken().toInt()
    }
    val dp = Array(n + 1) { i ->
        BooleanArray(n + 1) { j ->
            i == j
        }
    }

    for (i in 1 .. n) {
        for (j in 1 .. n - i) {
            val s = j
            val e = j + i
            if (s + 1 > e - 1) {
                dp[s][e] = nums[s - 1] == nums[e - 1]
            } else {
                dp[s][e] = (nums[s - 1] == nums[e - 1] && dp[s + 1][e - 1])
            }
        }
    }

    val m = br.readLine().toInt()
    val sb = StringBuilder()
    repeat(m) {
        st = StringTokenizer(br.readLine())
        val s = st.nextToken().toInt()
        val e = st.nextToken().toInt()
        sb.appendLine(if (dp[s][e]) 1 else 0)
    }

    println(sb.toString())
}