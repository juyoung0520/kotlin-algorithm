package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val s = br.readLine()
    val p = br.readLine()
    var count = 0

    var i = 0
    while (i < p.length) {
        var max = 0
        for (j in s.indices) {
            var tmp = 0
            while (s[j + tmp] == p[i + tmp]) {
                tmp++
                if (j + tmp >= s.length || i + tmp >= p.length) break
            }
            if (tmp > max) max = tmp
        }
        i += max
        count++
    }

    println(count)
}