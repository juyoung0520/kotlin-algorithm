package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val words = Array<String>(n) {
        br.readLine()
    }
    val letters = IntArray(26)
    for (c in words[0]) {
        letters[c - 'A']++
    }

    var count = 0
    for (i in 1 until words.size) {
        if (abs(words[i].length - words[0].length) > 1) continue // 문자열 길이 차이 2이상이면 무조건 false

        val copied = letters.copyOf()
        var same = 0
        for (c in words[i]) {
            if (copied[c - 'A'] > 0) {
                copied[c - 'A']--
                same++
            }
        }
        // 동일한 글자 수가 기준 문자열과 탐색 문자열의 길이와 각각 1 이하의 차이
        if (abs(words[i].length - same) <= 1 && abs(words[0].length - same) <= 1) {
            count++
        }
    }

    println(count)
}