package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val budgets = IntArray(n) {
        st.nextToken().toInt()
    }
    val total = br.readLine().toInt()

    var sum = 0
    var max = Int.MIN_VALUE
    for (b in budgets) {
        sum += b
        max = max.coerceAtLeast(b)
    }

    if (sum <= total) {
        println(max)
        return
    }

    var s = 1  // 상한액이 최소값보다 작을 수 있다.
    var e = max
    var mid: Int

    while (s <= e) {
        mid = (s + e) / 2

        var tmp = 0
        for (b in budgets) {
            val bb = if (b < mid) b else mid
            tmp += bb
        }

        if (tmp <= total) {
            s = mid + 1
        } else {
            e = mid - 1
        }
    }

    println(s - 1)
}