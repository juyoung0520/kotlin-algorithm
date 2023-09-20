package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val x = st.nextToken().toLong()
    val y = st.nextToken().toLong()
    val w = st.nextToken().toInt() // 직선
    val s = st.nextToken().toInt() // 대각선

    val min = if (x < y) x else y
    val diff = abs(x - y)

    // 최대한 대각선 이동
    val answer = if (s <= w) {
        min * s + if (diff % 2 == 0L) {
            diff * s
        } else {
            (diff - 1) * s + w
        }
    }
    // 모두 직선 이동
    else if (s > w * 2) {
        (x + y) * w
    } else { // w < s <= w * 2, 대각선 이동 + 직선 이동
        min * s + diff * w
    }

    println(answer)
}