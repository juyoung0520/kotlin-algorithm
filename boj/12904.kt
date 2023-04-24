package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val s = br.readLine()
    val t = br.readLine()
    val B = 'B'
    val sb = StringBuilder(t)

    while (sb.length > s.length) {
        val last = sb.last()
        sb.delete(sb.lastIndex, sb.lastIndex + 1)
        if (last == B) {
            sb.reverse()
        }
    }

    if (sb.toString() == s) println(1) else println(0)
}