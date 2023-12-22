package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    val arr = IntArray(n) {
        st.nextToken().toInt()
    }
    val sum = IntArray(n + 1)

    var max = Int.MIN_VALUE
    for (i in 1 .. n) {
        sum[i] = sum[i - 1] + arr[i - 1]
        if (i >= k) {
            max = max.coerceAtLeast(sum[i] - sum[i - k])
        }
    }

    println(max)
}