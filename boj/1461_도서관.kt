package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    val positions = IntArray(n) {
        st.nextToken().toInt()
    }

    positions.sort()

    var pivot = n
    for (i in positions.indices) {
        if (positions[i] > 0) {
            pivot = i
            break
        }
    }

    var sum = 0
    var i = 0
    while (i < pivot) {
        sum += -positions[i] * 2
        i += m
    }

    i = n - 1
    while (i >= pivot) {
        sum += positions[i] * 2
        i -= m
    }

    val max = abs(positions[0]).coerceAtLeast(abs(positions[n - 1]))
    println(sum - max)
}