package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val k = br.readLine().toInt()
    val sb = StringBuilder()
    var size = 1
    while (size < k) {
        size *= 2
    }
    sb.append(size).append(' ')

    var count = 0
    while (true) {
        if (k % size == 0) {
            sb.append(count)
            break
        } else {
            size /= 2
            count++
        }
    }
    println(sb.toString())
}