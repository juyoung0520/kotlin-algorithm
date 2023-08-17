package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

private const val YES = "YES"
private const val NO = "NO"
private var res = YES

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()
    val sb = StringBuilder()

    for(tc in 0 until t) {
        res = YES
        val paper = br.readLine().toCharArray()

        verify(paper, 0, paper.lastIndex)

        sb.appendLine(res)
    }

    println(sb.toString())
}

// 반으로 접기
private fun verify(paper: CharArray, start: Int, end: Int) {
    if (start >= end) {
        return
    }

    var s = start
    var e = end

    while (s < e) {
        if (paper[s++] == paper[e--]) {
            res = NO
            return
        }
    }

    verify(paper, start, e - 1) // 반으로 접기
}