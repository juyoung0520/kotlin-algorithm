package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val s = st.nextToken().toInt()
    val r = st.nextToken().toInt()

    val solved = IntArray(n + 1)
    var answer = s

    st = StringTokenizer(br.readLine())
    repeat(s) {
        val idx = st.nextToken().toInt()
        solved[idx]--
        idx
    }
    st = StringTokenizer(br.readLine())
    repeat(r) {
        val idx = st.nextToken().toInt()
        solved[idx]++
        // 자기 자신이 해결한 경우
        if (solved[idx] == 0) {
            answer--
        }
        idx
    }

    for (idx in solved.indices) {
        if (solved[idx] == 1) {
            if (idx - 1 >= 0 && solved[idx - 1] == -1) {
                solved[idx - 1]++
                answer--
            } else if (idx + 1 <= n && solved[idx + 1] == -1) {
                solved[idx + 1]++
                answer--
            }
        }
    }

    println(answer)
}