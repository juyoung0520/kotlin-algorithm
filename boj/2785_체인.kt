package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val arr = IntArray(n) {
        st.nextToken().toInt()
    }

    arr.sort()

    var count = 0
    var start = 0
    var end = n - 1
    while (end > start) {
        arr[start]--
        if (arr[start] == 0) {
            start++
        }
        end--
        count++
    }

    println(count)
}