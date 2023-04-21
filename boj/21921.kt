package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val x = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    val days = IntArray(n) {
        st.nextToken().toInt()
    }

    var end = 0
    var count = 0
    var max = 0
    var sum = 0
    for (i in days.indices) {
        while (end < days.size && end - i < x) {
            sum += days[end++]
        }

        if (end - i == x) {
            if (sum > max) {
                max = sum
                count = 1
            } else if (sum == max) {
                count++
            }
        } else {
            break
        }

        sum -= days[i]
    }

    if (max == 0) {
        println("SAD")
    } else {
        println("$max\n$count")
    }
}