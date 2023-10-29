package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val pos = mutableListOf<Int>()
    val neg = mutableListOf<Int>()

    st = StringTokenizer(br.readLine())
    repeat(n) {
        val num = st.nextToken().toInt()
        if (num < 0) {
            neg.add(num)
        } else {
            pos.add(num)
        }
    }

    neg.sort()
    pos.sortDescending()

    var max = if (neg.isNotEmpty()) -neg[0] else 0
    var sum = 0
    for (i in neg.indices) {
        val abs = -neg[i]
        if (i % m == 0) {
            sum += abs * 2
        }
    }

    for (i in pos.indices) {
        if (i % m == 0) {
            sum += pos[i] * 2
        }
    }

    if (pos.isNotEmpty()) {
        max = max.coerceAtLeast(pos[0])
    }

    println(sum - max)
}