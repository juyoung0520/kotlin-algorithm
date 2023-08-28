package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()
    val arr = Array(t) {
        br.readLine()
    }

    val sb = StringBuilder()
    for (i in arr.indices) {
        sb.appendLine(recursive(arr[i], 0, arr[i].lastIndex, 0))
    }

    println(sb.toString())
}

private fun recursive(str: String, start: Int, end: Int, count: Int): Int {
    var s = start
    var e = end
    while (s < e) {
        if (str[s] != str[e]) {
            // 다를 경우 두 가지 경우를 끝까지 확인해봐야한다. 단순 포문으로 한다면 다시 돌아갈 수 없음
            return if (count == 0) {
                if (recursive(str, s + 1, e, 1) == 0 || recursive(str, s, e - 1, 1) == 0) 1 else 2
            } else 2
        }
        s++
        e--
    }
    return 0
}