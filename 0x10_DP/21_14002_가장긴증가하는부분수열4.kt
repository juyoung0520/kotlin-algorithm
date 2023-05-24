package `kotlin-algorithm`.`0x10_DP`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.LinkedList
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val arr = IntArray(n) { st.nextToken().toInt() }

    val dp = IntArray(n) { 1 }
    val dp2 = IntArray(n) { -1 }
    var max = 0
    var maxIndex= 0
    for (i in dp.indices) {
        for (j in 0 until i) {
            if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                dp[i] = dp[j] + 1
                dp2[i] = j
            }
            if (dp[i] > max) {
                max = dp[i]
                maxIndex = i
            }
        }
    }

    var next = maxIndex
    val list = LinkedList<Int>()

    while (next >= 0) {
        list.addFirst(arr[next])
        next = dp2[next]
    }

    val sb = StringBuilder()
    sb.appendLine(dp[maxIndex])
    for (num in list) {
        sb.append(num, " ")
    }

    println(sb.toString())
}