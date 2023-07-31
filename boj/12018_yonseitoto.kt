package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val totalM = st.nextToken().toInt()
    val arr = IntArray(n)
    for (i in arr.indices) {
        st = StringTokenizer(br.readLine())
        val want = st.nextToken().toInt()
        val limit = st.nextToken().toInt()

        var min = 100
        st = StringTokenizer(br.readLine())
        val wants = IntArray(want) {
            st.nextToken().toInt()
        }
        if (limit > want) {
            arr[i] = 1
            continue
        }
        wants.sortDescending()
        arr[i] = wants[limit - 1]
    }

    arr.sort()
    var sum = 0
    var count = 0
    for (i in arr.indices) {
        if (sum + arr[i] > totalM) {
            break
        }
        count++
        sum += arr[i]
    }
    println(count)
}