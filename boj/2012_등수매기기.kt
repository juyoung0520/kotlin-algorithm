package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val arr = IntArray(n) {
        br.readLine().toInt()
    }
    arr.sort()

    var angrySize = 0L
    for (i in arr.indices) {
        angrySize += abs(arr[i] - (i + 1))
    }

    println(abs(angrySize))
}