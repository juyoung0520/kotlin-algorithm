package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val str = br.readLine()
    val find = br.readLine()

    var start = 0
    var count = 0
    var isCorrect = false
    while (start < str.length) {
        if (start + find.length > str.length) break
        for (i in find.indices) {
            if (find[i] != str[start + i]) {
                isCorrect = false
                break
            }
            if (i == find.lastIndex) {
                isCorrect = true
                count++
            }
        }
        if (isCorrect) {
            start += find.length
        } else {
            start++
        }
    }

    println(count)
}