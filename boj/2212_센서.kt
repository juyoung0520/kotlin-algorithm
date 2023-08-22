package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val k = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val arr = IntArray(n) {
        st.nextToken().toInt()
    }

    arr.sort()

    if (n <= k) {
        println(0)
        return
    }

    val distances = IntArray(arr.size - 1)
    for (i in 1 until arr.size) {
        distances[i - 1] = arr[i] - arr[i - 1]
    }

    distances.sort()

    var sum = 0
    for (i in 1 .. k - 1) {
        sum += distances[distances.size - i]
    }

    val full = arr.last() - arr[0]
    println(full - sum)
}